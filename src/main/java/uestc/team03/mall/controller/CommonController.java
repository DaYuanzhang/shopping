package uestc.team03.mall.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.utils.Result;
import uestc.team03.mall.service.UserService;

@Controller
public class CommonController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page/{path}")
    public String toPage1(@PathVariable("path") String p){
        return "/view/"+p;
    }


}
