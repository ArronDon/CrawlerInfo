package service;

import domain.DianpingComment;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arron on 2016/9/2.
 */

public class DianpingCommentServiceTest extends TestCase {
    @Autowired
    private DianpingCommentService dianpingCommentService;

    @Test
    public void testFindCommentById() throws Exception {
        System.out.println("here");
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DianpingCommentService dianpingCommentService=(DianpingCommentService) ctx.getBean("dianpingCommentService");
        DianpingComment comment = dianpingCommentService.findCommentById(2);
        System.out.println(comment.getContent());
    }

}