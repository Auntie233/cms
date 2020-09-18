package top.auntie.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

/**
 *
 * @author Auntie
 * @date 2016/8/3
 */
@Controller
public class CommonController {

    @RequestMapping("/index.html")
    public String index(Map<String,Object> map){
        return "index";
    }

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

}
