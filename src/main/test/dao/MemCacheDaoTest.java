package dao;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by arron on 2016/9/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-memcached.xml")
public class MemCacheDaoTest extends TestCase {
    @Autowired
    MemCacheDao dao;
    @org.junit.Test
    public void testGetfromCache() throws Exception {
        //dao.settoCache("hello");
        String res=dao.getfromCache("tennisplayer");
        System.out.println("res:"+res);
    }

}