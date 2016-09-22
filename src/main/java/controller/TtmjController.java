package controller;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BaseGetInfoMainService;

/**
 * Created by arron on 2016/9/22.
 */
@Controller
public class TtmjController {
    private static final Logger logger=Logger.getLogger(TtmjController.class);
    @RequestMapping("/downloader")
    public String downloader(String url){
        try{
            //String url=new String(request.getParameter("url").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("url:"+url);
            ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            BaseGetInfoMainService ttmjMainService=(BaseGetInfoMainService) ctx.getBean("ttmjMainService");
            ttmjMainService.mainService(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "downloader";
    }
}
