package dao;

import entity.Comment;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by arron on 2016/8/30.
 */
public class CommentDaoImpl implements CommentDao {
    private SqlSession session;

    //根据id查询评论
    public Comment findCommentById(int id) {
        SessionDao sessionDao = new SessionDao();
        this.session = sessionDao.getSession();
        String statement = "commentMapper.findCommentById";
        Comment comment = (Comment) session.selectOne(statement, id);
        session.close();
        return comment;
    }

    //添加评论
    public void addComment(Comment comment) {
        SessionDao sessionDao = new SessionDao();
        this.session = sessionDao.getSession();
        String statement = "commentMapper.addComment";
        session.insert(statement, comment);
        //务必记得commit
        session.commit();
        session.close();
    }

    public synchronized void addComments(List<Comment> commentList) {
        SessionDao sessionDao = new SessionDao();
        this.session = sessionDao.getSession();
        String statement = "commentMapper.addComment";
        for (Comment comment : commentList
                ) {
            session.insert(statement, comment);
        }
        session.commit();
        session.close();
    }
}
