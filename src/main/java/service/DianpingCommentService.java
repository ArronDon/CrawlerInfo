package service;

import domain.DianpingComment;

import java.util.List;

/**
 * Created by arron on 2016/9/2.
 */
public interface DianpingCommentService {
    DianpingComment findCommentById(int id);

    void addComment(DianpingComment dpc);

    void addComments(List<DianpingComment> dpList);
}
