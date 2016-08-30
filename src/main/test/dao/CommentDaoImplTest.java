package dao;

import entity.Comment;
import junit.framework.TestCase;
import servlet.HtmlProcessServlet;
import servlet.ReadFileServlet;

import java.util.List;

/**
 * Created by arron on 2016/8/30.
 */
public class CommentDaoImplTest extends TestCase {
    public void testFindCommentById() throws Exception {
        CommentDaoImpl commentDao = new CommentDaoImpl();
        Comment comment = commentDao.findCommentById(2);
        System.out.println(comment.getUsername() + ":" + comment.getContent());
    }

    public void testAddComment() throws Exception {
        Comment comment = new Comment();
        comment.setUsername("xuehua75");
        comment.setTaste("口味4");
        comment.setEnvironment("环境4");
        comment.setService("服务4");
        comment.setContent("抽到的霸王餐，蛋糕很好吃！很不错");
        comment.setComment_time("08-29");
        CommentDaoImpl commentDao = new CommentDaoImpl();
        commentDao.addComment(comment);
    }

    public void testAddComments() throws Exception{
        ReadFileServlet readFileServlet=new ReadFileServlet();
        String response=readFileServlet.readfromFile(ReadFileServlet.class.getResource("/page.html").getPath());
        HtmlProcessServlet htmlProcessServlet=new HtmlProcessServlet();
        List<Comment> commentList=htmlProcessServlet.getCommentList(response);
        CommentDaoImpl commentDao=new CommentDaoImpl();
        commentDao.addComments(commentList);
    }
}