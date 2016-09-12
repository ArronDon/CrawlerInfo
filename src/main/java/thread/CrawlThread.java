package thread;

import crawl.HttpClientProcessor;
import dao.CommentDaoImpl;
import entity.Comment;
import org.apache.log4j.Logger;
import servlet.HtmlProcessServlet;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by arron on 2016/8/31.
 */
public class CrawlThread implements Runnable {
    private static Logger logger=Logger.getLogger(CrawlThread.class);
    private String url;
    private List<Comment> list;
    private HashMap<String,String> params;

//    private CommentDaoImpl commentDao;
    public CrawlThread(String url, HashMap<String,String> params) {
        this.url = url;
        this.params=params;
    }

    public void run() {
        HttpClientProcessor clientProcessor = new HttpClientProcessor();
        String response = clientProcessor.getResponse(url,params);
        logger.info(url+":response length-"+response.length());
        HtmlProcessServlet htmlProcessServlet=new HtmlProcessServlet();
        list=htmlProcessServlet.getCommentList(response);
        CommentDaoImpl commentDao=new CommentDaoImpl();
        commentDao.addComments(list);
        Random random=new Random();

        int time=random.nextInt(9000);

        System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"：进入睡眠");
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"：结束睡眠");
    }

    public String call() throws Exception {
        HttpClientProcessor clientProcessor = new HttpClientProcessor();
        String response = clientProcessor.getResponse(url,params);
        logger.info(url+":response length-"+response.length());
        if(response==null||response.equals("")) {
            System.out.println("alarm----"+url+"获取为空");

            return "false";
        }
        HtmlProcessServlet htmlProcessServlet=new HtmlProcessServlet();
        list=htmlProcessServlet.getCommentList(response);
        CommentDaoImpl commentDao=new CommentDaoImpl();
        commentDao.addComments(list);
        Random random=new Random();

        int time=random.nextInt(9000);

        System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"：进入睡眠");
        Thread.sleep(time);
        System.out.println(Thread.currentThread().getName()+":"+System.currentTimeMillis()+"：结束睡眠");
        return url;
    }
}
