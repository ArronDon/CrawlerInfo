package service;

import domain.DianpingComment;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arron on 2016/9/2.
 */

public class DianpingCommentDaoServiceTest extends TestCase {
    @Autowired
    private DianpingCommentDaoService dianpingCommentService;

    @Test
    public void testFindCommentById() throws Exception {
        System.out.println("here");
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DianpingCommentDaoService dianpingCommentService=(DianpingCommentDaoService) ctx.getBean("dianpingCommentService");
        DianpingComment comment = dianpingCommentService.findCommentById(2);
        System.out.println(comment.getContent());
    }

}