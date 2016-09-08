package resource;

import junit.framework.TestCase;
import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import servlet.ReadFileServlet;

/**
 * Created by arron on 2016/9/8.
 */
public class ApplicationContextTest extends TestCase{
    @Test
    public void testApplicationContext() throws Exception{
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        ReadFileServlet reader=new ReadFileServlet();
        String str=reader.readfromFile(ReadFileServlet.class.getResource("/page.html").getPath());
        MemcachedClient client=(MemcachedClient) ctx.getBean("xmemcachedClient");
        client.add("html",3000000,str);
        client.add("name",3000000,"德尔波特罗");
        System.out.println("html added");
        System.out.println(client.get("html"));
    }
}
