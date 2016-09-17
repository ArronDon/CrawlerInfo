package thread;

import domain.Episode;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TtmeijuHandlerService;
import service.TtmjDaoService;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by arron on 2016/9/17.
 */
public class TtmjSaverThread implements Runnable {
    private static final Logger logger = Logger.getLogger(TtmjSaverThread.class);
    private String url;

    public TtmjSaverThread(String url) {
        this.url = url;
    }

    public void run() {
        logger.info("ttsaver is running");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MemcachedClient client = (MemcachedClient) ctx.getBean("xmemcachedClient");
        String response = null;
        try {
            response = client.get(url);
            logger.info(Thread.currentThread().getName() + " - " + response.length());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        TtmeijuHandlerService ttmjHandlerService = (TtmeijuHandlerService) ctx.getBean("ttmjHandlerService");
        List<Episode> list = ttmjHandlerService.getEpisodeList(response);
        for (Episode e : list
                ) {
            logger.info(e.getSeries_name() + " " + e.getLinks() + " " + e.getVideo_size());
        }
        logger.info("list大小为:" + list == null ? 0 : list.size());
        TtmjDaoService ttmjDaoService = (TtmjDaoService) ctx.getBean("ttmjDaoService");
        try {
            ttmjDaoService.addEpisodes(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
