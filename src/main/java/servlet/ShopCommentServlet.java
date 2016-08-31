package servlet;

import crawl.HttpClientProcessor;
import dao.CommentDaoImpl;
import thread.CrawlThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by arron on 2016/8/31.
 */
public class ShopCommentServlet {
    public void getAllComments(String url){
        //获取评论默认页面内容
        HttpClientProcessor clientProcessor=new HttpClientProcessor();
        String response=clientProcessor.getResponse(url);

        //获取页面中评论总页数
        HtmlProcessServlet htmlProcessServlet=new HtmlProcessServlet();
        int count=htmlProcessServlet.getPageCount(response);
        //创建url队列
        Queue<String> queue = new LinkedList<String>();
        String baseUrl = "https://www.dianping.com/shop/21616826/review_all?pageno=";
        for (int i = 1; i <= count; i++) {
            String str = baseUrl + i;
            queue.offer(str);
        }
        ExecutorService exec= Executors.newFixedThreadPool(5);
        ArrayList<Future<String>> results=new ArrayList<Future<String>>();
        CommentDaoImpl commentDao=new CommentDaoImpl();
        while (queue.peek() != null) {
            String str = queue.remove();
            System.out.println(url);
            results.add(exec.submit(new CrawlThread(str,commentDao)));
        }
        System.out.println("--------------This is results---------------");
        for (Future<String> fs:results
             ) {
            try {
                System.out.println(fs.get());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }
}
