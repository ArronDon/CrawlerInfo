package service;

import domain.DianpingComment;

import java.util.List;

/**
 * Created by arron on 2016/9/2.
 */
public interface DianpingCommentHandlerService {
    int getPagesAmount(String body);
    List<DianpingComment> getCommentList(String body);
}
