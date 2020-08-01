package uestc.team03.mall.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import uestc.team03.mall.common.domain.User;

import javax.servlet.http.HttpSession;
import java.util.IdentityHashMap;


@Controller
public class CommonController {


    @RequestMapping("/")
    public String tologin(){
        return "/view/login";
    }

    @RequestMapping("/{path}")
    public String toPage1(@PathVariable("path") String p,ModelMap modelMap, HttpSession session){
        User user1= (User)session.getAttribute("user");
        modelMap.put("user",user1);
        return "/view/"+p;
    }

    @RequestMapping("/AdminPage")
    public String toPage1(ModelMap modelMap, HttpSession session){
        User user2= (User)session.getAttribute("user");
        modelMap.put("user",user2);
        return "/view/AdminPage";
    }


}