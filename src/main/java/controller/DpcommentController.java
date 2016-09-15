package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DianpingMainServiceImpl;

/**
 * Created by arron on 2016/9/14.
 */
@Controller
public class DpcommentController {
    private static final Logger logger=Logger.getLogger(DpcommentController.class);
//    @Autowired
//    BaseGetInfoMainService dianpingMainService;
    @RequestMapping("/dpcomment")
    public String dpcomment(String url){

        try{
            //String url=new String(request.getParameter("url").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("url:"+url);
            DianpingMainServiceImpl dpService=new DianpingMainServiceImpl();
            dpService.mainService(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "dpcomment";
    }
}
