package service;

import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thread.CrawlerThread;
import thread.TtmjSaverThread;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by arron on 2016/9/17.
 */
@Service("ttmjMainService")
public class TtmeijuMainServiceImpl implements BaseGetInfoMainService{
    private static final Logger logger=Logger.getLogger(TtmeijuMainServiceImpl.class);
    @Autowired
    HttpClientServiceImpl httpClientService;
    @Autowired
    TtmeijuHandlerService ttmjHandlerService;
    public List<String> setGetParams(String url){
        String cookie="bdshare_firstime=1472697997959; popped=yes; Hm_lvt_52542b48446553babc42e4baae658e27=1472697953,1472781256,1473762952; Hm_lpvt_52542b48446553babc42e4baae658e27=1474084646";
        String user_agent="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
        String host="www.ttmeiju.com";
        return Arrays.asList(cookie,host,user_agent);

    }
    public Map<String, String> genereateHttpGetParamsList(List<String> args) {
        HashMap<String,String > paramsMap=new HashMap<String, String>();
        paramsMap.put("Cookie",args.get(0));
        paramsMap.put("Host",args.get(1));
        paramsMap.put("User-Agent",args.get(2));
        return paramsMap;
    }

    //设置下载页面队列
    public Queue<String> setDownloadUrlQueue(String baseUrl, int count) {
        Queue<String> queue=new LinkedList<String>();
        //页码从1开始
        for(int i=1;i<=count;i++){
            String url=baseUrl+i;
            queue.offer(url);
        }
        return queue;
    }

    public void mainService(String url) {
        logger.info("ttmjmainserviceimpl.class");
        //设置请求报文参数
        Map<String,String> paramsMap=genereateHttpGetParamsList(setGetParams(url));
        HttpGet request=httpClientService.createGetRequest(url,paramsMap);
        String response=httpClientService.getGetResponse(request);
        logger.info("response.length="+response.length());
        int pageNum= ttmjHandlerService.getPagesAmount(response);
        Queue<String> downloadUrlQueue=setDownloadUrlQueue(url+"?page=",pageNum);
        Queue<String> cachedUrlQueue=new LinkedList<String>();
        //获取剧集下载列表
        ExecutorService crawlExec= Executors.newFixedThreadPool(5);
        while (downloadUrlQueue.peek()!=null){
            String link=downloadUrlQueue.remove();
            logger.info("download queue: "+link);
            cachedUrlQueue.offer(link);
            crawlExec.submit(new CrawlerThread(link,paramsMap));
        }
        crawlExec.shutdown();
        ExecutorService saverExec= Executors.newFixedThreadPool(5);
        while(cachedUrlQueue.peek()!=null){
            String key=cachedUrlQueue.remove();
            saverExec.submit(new TtmjSaverThread(key));
        }
        saverExec.shutdown();
        //ttmjHandlerService.getEpisodeList(response);
        logger.info(pageNum);
        //主线程等待子线程执行完毕后再退出
        try{
            while(!saverExec.awaitTermination(10, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
