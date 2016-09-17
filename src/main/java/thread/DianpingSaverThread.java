package thread;

import com.sun.istack.internal.logging.Logger;
import domain.DianpingComment;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ContextUtil;
import service.DianpingCommentDaoService;
import service.DianpingCommentHandlerService;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by arron on 2016/9/8.
 */
public class DianpingSaverThread implements Runnable {
    private static Logger logger = Logger.getLogger(DianpingSaverThread.class);
    private String url;

    public DianpingSaverThread(String url) {
        this.url = url;
    }

    public void run() {
        logger.info("dpsaver thread is running");
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MemcachedClient client=(MemcachedClient) ctx.getBean("xmemcachedClient");
        String response=null;
        try {
            logger.info(Thread.currentThread().getName());
            response=client.get(url);
            logger.info(url+"长短sa"+response.length());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        DianpingCommentHandlerService handler = (DianpingCommentHandlerService) ContextUtil.getBean
                ("dianpingCommentHandlerService");
        logger.info("saver:"+url+"---"+response.length());
        List<DianpingComment> list=handler.getCommentList(response);
        logger.info(url+"列表大小为："+list.size());

        DianpingCommentDaoService commentService=(DianpingCommentDaoService) ContextUtil.getBean("dianpingCommentService");
        commentService.addComments(list);
    }
}
