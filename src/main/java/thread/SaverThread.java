package thread;

import com.sun.istack.internal.logging.Logger;
import domain.DianpingComment;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ContextUtil;
import service.DianpingCommentHandlerService;
import service.DianpingCommentService;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by arron on 2016/9/8.
 */
public class SaverThread implements Runnable {
    private static Logger logger = Logger.getLogger(SaverThread.class);
    private String url;

    public SaverThread(String url) {
        this.url = url;
    }

    public void run() {
        logger.info("saver thread is running");
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MemcachedClient client=(MemcachedClient) ctx.getBean("xmemcachedClient");
        String response=null;
        try {
            logger.info(url);
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
//        if(list.size()==20){
//            for(int i=0;i<20;i++) {
//                DianpingComment comment=list.get(i);
//                logger.info(comment.getUsername()+":"+comment.getContent()+"<>"+comment.getShop_name());
//            }
//        }
        DianpingCommentService commentService=(DianpingCommentService) ContextUtil.getBean("dianpingCommentService");
        commentService.addComments(list);
    }
}
