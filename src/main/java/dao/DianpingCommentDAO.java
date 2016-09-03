package dao;

import domain.DianpingComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by arron on 2016/9/2.
 */
@Repository
public interface DianpingCommentDao {
    DianpingComment findCommentById(int id);

    void addComment(DianpingComment dpc);

    void addComments(List<DianpingComment> dpList);

}
