package dao;

import entity.Comment;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

/**
 * Created by arron on 2016/9/1.
 */
public class MemConnection {
    private static final Logger logger=Logger.getLogger(MemConnection.class);
    public void getClient() throws Exception{

    }
    public void addComment(Comment comment) throws Exception{
        MemcachedClient client=new MemcachedClient(new InetSocketAddress("192.168.99.132",11211));
        client.set("test1",2000000,comment);
        comment=(Comment)client.get("test1");
        System.out.println(comment.getContent());
        client.shutdown();
    }
    public void getComment(String key) throws Exception{

    }
    public void setResponse(String key,String response) throws Exception{
        MemcachedClient client=new MemcachedClient(new InetSocketAddress("192.168.99.132",11211));
        logger.info("set response:"+key+response);

        client.set(key,2000000,response);
    }
    public void getResponse(String key) throws Exception{
        MemcachedClient client=new MemcachedClient(new InetSocketAddress("192.168.99.132",11211));
        logger.info("get response:"+key);
        String response=(String)client.get(key);
        logger.info(response);
    }
}
