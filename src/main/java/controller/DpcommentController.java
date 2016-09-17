package controller;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BaseGetInfoMainService;

/**
 * Created by arron on 2016/9/14.
 */
@Controller
public class DpcommentController {
    private static final Logger logger=Logger.getLogger(DpcommentController.class);
    //@Autowired
    //BaseGetInfoMainService dianpingMainService;
    @RequestMapping("/dpcomment")
    public String dpcomment(String url){

        try{
            //String url=new String(request.getParameter("url").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("url:"+url);
            ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            BaseGetInfoMainService dianpingMainService=(BaseGetInfoMainService) ctx.getBean("dianpingMainService");
            dianpingMainService.mainService(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "downloader";
    }
}
