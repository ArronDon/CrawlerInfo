package thread;

import com.sun.istack.internal.logging.Logger;
import domain.DianpingComment;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import service.DianpingCommentHandlerServiceImpl;
import service.DianpingCommentServiceImpl;
import service.HttpClientServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by arron on 2016/9/3.
 */
public class CrawlerThread implements Runnable {
    private static Logger logger= Logger.getLogger(CrawlerThread.class);
    private String url;
    private List<DianpingComment> list;
    private Map<String, String> paramsMap;
    @Autowired
    private DianpingCommentHandlerServiceImpl dianpingHandler;
    @Autowired
    private HttpClientServiceImpl httpClientService;
    @Autowired
    private DianpingCommentServiceImpl dianpingCommentService;

    public CrawlerThread(String url, Map<String, String> paramsMap) {
        this.url = url;
        this.paramsMap = paramsMap;
    }

    public void run() {
        logger.info("here in thread");
        if(httpClientService==null)
            logger.info("its null");
        else {
            logger.info("its not null");
        }
        HttpGet request = httpClientService.createGetRequest(url, paramsMap);
        logger.info(request.toString());
        String response = httpClientService.getGetResponse(request);
        logger.info("response here"+Thread.currentThread().getName());
        list = dianpingHandler.getCommentList(response);
        Random random = new Random();
        int time = random.nextInt(9000);
        dianpingCommentService.addComments(list);
        System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis() + "：进入睡眠");
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis() + "：结束睡眠");
    }
}
