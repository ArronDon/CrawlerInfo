package service;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arron on 2016/9/3.
 */
public class DianpingMainServiceImplTest extends TestCase {

    @Autowired
    private BaseGetInfoMainService dianpingMainService;
    @Test
    public void testDianpingMainServiceImpl(){
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        dianpingMainService=(BaseGetInfoMainService) ctx.getBean("dianpingMainService");
        dianpingMainService.mainService();

    }
}