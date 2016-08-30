package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arron on 2016/8/30.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/nice",method = RequestMethod.GET)
    public String nice(Model model){
        model.addAttribute("result","haha 第一个参数");
        return "nice";
    }
}
