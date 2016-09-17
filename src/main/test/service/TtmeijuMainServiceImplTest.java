package service;

import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arron on 2016/9/17.
 */
public class TtmeijuMainServiceImplTest extends TestCase {
    @Autowired
    private BaseGetInfoMainService ttmjMainService;
    public void testMainService() throws Exception {
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ttmjMainService=(BaseGetInfoMainService) ctx.getBean("ttmjMainService");
        ttmjMainService.mainService("http://www.ttmeiju.com/meiju/Narcos.html");
    }

}