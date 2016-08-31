package thread;

import crawl.HttpClientProcessor;
import dao.CommentDaoImpl;
import entity.Comment;
import servlet.HtmlProcessServlet;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by arron on 2016/8/31.
 */
public class CrawlThread implements Callable<String> {
    private String url;
    private List<Comment> list;
    private CommentDaoImpl commentDao;
    public CrawlThread(String url,CommentDaoImpl commentDao) {
        this.url = url;
        this.commentDao=commentDao;
    }

    public void run() {
        HttpClientProcessor clientProcessor = new HttpClientProcessor();
        String response = clientProcessor.getResponse(url);
        HtmlProcessServlet htmlProcessServlet=new HtmlProcessServlet();
        list=htmlProcessServlet.getCommentList(response);
    }

    public String call() throws Exception {
        HttpClientProcessor clientProcessor = new HttpClientProcessor();
        String response = clientProcessor.getResponse(url);
        if(response==null||response.equals("")) {
            System.out.println("alarm----"+url+"获取为空");

            return "false";
        }
        HtmlProcessServlet htmlProcessServlet=new HtmlProcessServlet();
        list=htmlProcessServlet.getCommentList(response);
        //CommentDaoImpl commentDao=new CommentDaoImpl();
        commentDao.addComments(list);
        return url;
    }
}
