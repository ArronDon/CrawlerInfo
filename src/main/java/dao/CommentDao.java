package dao;

import entity.Comment;

import java.util.List;

/**
 * Created by arron on 2016/8/30.
 */
public interface CommentDao {
    public Comment findCommentById(int id);
    public void addComment(Comment comment);
    public void addComments(List<Comment> commentList);
}
