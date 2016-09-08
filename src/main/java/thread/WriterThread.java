package thread;

import com.sun.istack.internal.logging.Logger;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arron on 2016/9/1.
 */
public class WriterThread implements Runnable {
    private static Logger logger = Logger.getLogger(WriterThread.class);
    private String response;
    private String url;

    WriterThread(String url, String response) {
        this.response = response;
        this.url = url;
    }

    public void run() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MemcachedClient client = (MemcachedClient) ctx.getBean("xmemcachedClient");
        try {
            logger.info("writer:" + url.length() + "--" + url + "-response size:" + response.length());
            client.add(url, 3000, response);

            Thread thread = new Thread(new SaverThread(url));
            thread.run();
            //System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
