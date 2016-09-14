package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arron on 2016/8/30.
 */
@Controller
public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        //model.addAttribute("result","haha 绗竴涓弬鏁�");
        logger.info("requested here");
        return "index";
    }

    @RequestMapping(value = "/dpcomment", method = RequestMethod.GET)
    public String dpcomment() {
        logger.info("redirect to dpcomment.jsp");
        return "dpcomment";
    }

    @RequestMapping(value = "/downloader", method = RequestMethod.GET)
    public String downloaderRedirect() {
        logger.info("redirect to downloader.jsp");
        return "downloader";
    }
}
