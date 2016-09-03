package dao;

import entity.Comment;
import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;

/**
 * Created by arron on 2016/9/1.
 */
public class MemConnection {
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
}
