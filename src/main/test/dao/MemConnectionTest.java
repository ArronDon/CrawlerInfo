package dao;

import entity.Comment;
import junit.framework.TestCase;

/**
 * Created by arron on 2016/9/1.
 */
public class MemConnectionTest extends TestCase {
    public void testAddComment() throws Exception {
        CommentDaoImpl commentDao=new CommentDaoImpl();
        Comment comment=commentDao.findCommentById(1);
        MemConnection memConnection=new MemConnection();
        memConnection.addComment(comment);
    }

}