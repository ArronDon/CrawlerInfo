package thread;

import com.sun.istack.internal.logging.Logger;
import domain.DianpingComment;
import net.rubyeye.xmemcached.MemcachedClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by arron on 2016/9/3.
 */
public class CrawlerThread implements Runnable {
    private static Logger logger = Logger.getLogger(CrawlerThread.class);
    private String url;
    private List<DianpingComment> list;
    private Map<String, String> paramsMap;
    //    @Autowired
    private static DianpingCommentHandlerService dianpingHandler;
    //    @Autowired
    private static HttpClientService httpClientService;
    //    @Autowired
    private static DianpingCommentService dianpingCommentService;

    public CrawlerThread(String url, Map<String, String> paramsMap) {
        this.url = url;
        this.paramsMap = paramsMap;
    }

    public void run() {
        httpClientService = (HttpClientServiceImpl) ContextUtil.getBean("httpClientService");
        HttpGet request = httpClientService.createGetRequest(url, paramsMap);
        //logger.info("request size" + request.getAllHeaders());
        String response = httpClientService.getGetResponse(request);
        //logger.info("response here" + response.length());
        logger.info("crawler thread:"+url+"---"+response.length());
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MemcachedClient client = (MemcachedClient) ctx.getBean("xmemcachedClient");
        try {
            //logger.info("writer:" + url.length() + "--" + url + "-response size:" + response.length());
            client.add(url, 3000, response);
            logger.info("crawler thread:"+url+"added");
            //Thread thread = new Thread(new DianpingSaverThread(url));
            //thread.run();
            //System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Thread writer=new Thread(new WriterThread(url,response));
//        writer.run();
        /*dianpingHandler = (DianpingCommentHandlerService) ContextUtil.getBean("dianpingCommentHandlerService");
        list = dianpingHandler.getCommentList(response);*/
        Random random = new Random();
        int time = random.nextInt(9000);
        /*dianpingCommentService = (DianpingCommentService) ContextUtil.getBean("dianpingCommentService");
        dianpingCommentService.addComments(list);
        System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis() + "：进入睡眠");*/
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis() + "：结束睡眠");
*/
    }
}
