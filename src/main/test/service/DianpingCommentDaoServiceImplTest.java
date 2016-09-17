package service;

import domain.DianpingComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by arron on 2016/9/2.
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DianpingCommentDaoServiceImplTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private DianpingCommentDaoService dianpingCommentService;
    @Test
    public void testFindCommentById() throws Exception {
        DianpingComment dianpingComment=dianpingCommentService.findCommentById(2);
        System.out.println(dianpingComment.getUsername());
    }

}