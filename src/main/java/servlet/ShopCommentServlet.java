package servlet;

import crawl.HttpClientProcessor;
import dao.CommentDaoImpl;
import org.apache.log4j.Logger;
import thread.CrawlThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by arron on 2016/8/31.
 */
public class ShopCommentServlet {
    private static Logger logger = Logger.getLogger(ShopCommentServlet.class);

    public void getAllComments(String url) {
        //获取评论默认页面内容
        HashMap<String, String> paramsList = new HashMap<String, String>();
        paramsList.put("Cookie", "_hc.v=104a7fb9-83c8-ea32-73b0-5f3567c626d4.1472458871; __utma=1.2039981199" +
                ".1472458881.1472458881.1472458881.1; __utmc=1; __utmz=1.1472458881.1.1.utmcsr=google.co.jp|utmccn=" +
                "(referral)|utmcmd=referral|utmcct=/; PHOENIX_ID=0a650029-156d563a258-7a5935; s_ViewType=10; " +
                "JSESSIONID=36353641A47CF6971DE5469D3883F125; aburl=1; cy=2; cye=beijing");
        paramsList.put("Host", "www.dianping.com");
        paramsList.put("Referer",url);
        paramsList.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/52.0.2743.116 Safari/537.36");
        HttpClientProcessor clientProcessor = new HttpClientProcessor();
        String response = clientProcessor.getResponse(url,paramsList);
        logger.info(response);
        //获取页面中评论总页数
        HtmlProcessServlet htmlProcessServlet = new HtmlProcessServlet();
        int count = htmlProcessServlet.getPageCount(response);
        //创建url队列
        Queue<String> queue = new LinkedList<String>();
        String baseUrl = url+"?pageno=";
        for (int i = 1; i <= count; i++) {
            String str = baseUrl + i;
            //System.out.println(str);
            queue.offer(str);
        }
        ExecutorService exec = Executors.newFixedThreadPool(5);
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        CommentDaoImpl commentDao = new CommentDaoImpl();
        while (queue.peek() != null) {
            String str = queue.remove();
            logger.info(str);
            exec.submit(new CrawlThread(str,paramsList));
        }

        System.out.println("--------------This is results---------------");
        /*for (Future<String> fs : results
                ) {
            try {
                //System.out.println(fs.get());
                logger.info(fs.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }*/
        try{
            while(!exec.awaitTermination(60, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
