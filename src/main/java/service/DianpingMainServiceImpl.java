package service;

import com.sun.istack.internal.logging.Logger;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thread.CrawlerThread;
import thread.SaverThread;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by arron on 2016/9/3.
 */
@Service("dianpingMainService")
public class DianpingMainServiceImpl implements BaseGetInfoMainService {

    @Autowired
    private DianpingCommentHandlerServiceImpl dianpingCommentHandlerService;
    @Autowired
    private HttpClientServiceImpl httpClientService;

    private static Logger logger= Logger.getLogger(DianpingMainServiceImpl.class);
    public List setGetParams() {
        String cookie = "_hc.v=104a7fb9-83c8-ea32-73b0-5f3567c626d4.1472458871; __utma=1.2039981199" +
                ".1472458881.1472458881.1472458881.1; __utmc=1; __utmz=1.1472458881.1.1.utmcsr=google.co.jp|utmccn=" +
                "(referral)|utmcmd=referral|utmcct=/; PHOENIX_ID=0a650029-156d563a258-7a5935; s_ViewType=10; " +
                "JSESSIONID=36353641A47CF6971DE5469D3883F125; aburl=1; cy=2; cye=beijing";
        String host = "www.dianping.com";
        String refer = "http://www.dianping.com/shop/511543/review_all";//23721600//57214833/4183681
        String user_agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/52.0.2743.116 Safari/537.36";
        return Arrays.asList(cookie, host, refer, user_agent);
    }
//    public Map<String, String> generateHttpGetParamsList(String cookie, String host, String referer, String
// user_agent) {
//
//
//        Map<String,String> params=new HashMap<String, String>();
//        params.put("Cookie",cookie);
//        params.put("Host",host);
//        params.put("Referer",referer);
//        params.put("User-Agent",user_agent);
//        return params;
//    }

    public Map<String, String> genereateHttpGetParamsList(List<String> args) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("Cookie", args.get(0));
        params.put("Host", args.get(1));
        params.put("Referer", args.get(2));
        params.put("User-Agent", args.get(3));
        return params;
    }

    public Queue<String> setDownloadUrlQueue(String baseUrl, int count) {
        Queue<String> queue=new LinkedList<String>();
        for(int i=1;i<=count;i++){
            String str=baseUrl+i;
            //logger.info(str);
            queue.offer(str);
        }

        return queue;
    }

    public void mainService() {

        String url="http://www.dianping.com/shop/23721600/review_all";
                //"https://www.dianping.com/shop/32333630/review_all";
        Map<String,String> paramsMap=genereateHttpGetParamsList(setGetParams());
        HttpGet request=httpClientService.createGetRequest(url,paramsMap);
        logger.info(request.toString());
        String response=httpClientService.getGetResponse(request);
        //logger.info(response);
        int pagesNum=dianpingCommentHandlerService.getPagesAmount(response);
        Queue<String> downloadUrlQueue=setDownloadUrlQueue(url+"?pageno=",pagesNum);
        Queue<String> cachedUrlQueue=new LinkedList<String>();
        ExecutorService exec= Executors.newFixedThreadPool(5);
//        String link=downloadUrlQueue.remove();
//        logger.info(link);
//        Thread thread=new Thread(new CrawlerThread(link,paramsMap));
//        thread.run();
        while (downloadUrlQueue.peek()!=null){
            String link=downloadUrlQueue.remove();
            cachedUrlQueue.offer(link);
            logger.info("download:"+link);
            exec.submit(new CrawlerThread(link,paramsMap));

        }
        ExecutorService saverExec=Executors.newFixedThreadPool(5);
        while (cachedUrlQueue.peek()!=null){
            String key=cachedUrlQueue.remove();
            exec.submit(new SaverThread(key));
        }
        //主线程等待子线程执行完毕后再退出
        try{
            while(!exec.awaitTermination(30, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
