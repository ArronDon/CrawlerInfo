package service;

import com.sun.istack.internal.logging.Logger;
import dao.DianpingCommentDao;
import domain.DianpingComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by arron on 2016/9/2.
 */
@Service("dianpingCommentService")
public class DianpingCommentServiceImpl implements DianpingCommentService {
    //@Resource(name = "dianpingCommentDaO")
    private static Logger logger = Logger.getLogger(DianpingCommentServiceImpl.class);
    @Autowired
    private DianpingCommentDao dianpingCommentDao;

    public DianpingComment findCommentById(int id) {
        return dianpingCommentDao.findCommentById(id);
    }

    public void addComment(DianpingComment dpc) {
        dianpingCommentDao.addComment(dpc);
    }

    public void addComments(List<DianpingComment> dpList) {
        if(dpList.size()==0)
            return;
        for (DianpingComment comment :
                dpList) {
            dianpingCommentDao.addComment(comment);
        }

        logger.info("added a list");
    }
}
