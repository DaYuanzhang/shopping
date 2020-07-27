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
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/AdminPage")
    public String AdminPage(){
        return "AdminPage";
    }

    @RequestMapping("/page/{path}")
    public String toPage1(@PathVariable("path") String p){
        return "/view/"+p;
    }

    @RequestMapping("/listConsumer")
    @ResponseBody
    public Object listConsumer(User user,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10")int pageSize){
        PageInfo<User> pageInfo = userService.findConsumer(pageNo, pageSize,user);
        return Result.success(pageInfo);
    }

    @RequestMapping("/showConsumer")
    public String showConsumer(ModelMap modelMap, Integer id){
        User user = userService.findUserById(id);
        modelMap.put("user",user);
        return "/view/showConsumerDetail";
    }

    @RequestMapping("/delConsumer")
    @ResponseBody
    public Object delConsumer(@RequestBody Integer[] ids){
       int count = userService.removeUser(ids);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/updConsumer")
    public String updConsumer(ModelMap modelMap,Integer id){
        User user = userService.findUserById(id);
        modelMap.put("user",user);
        return "/view/showConsumerUpdate";
    }

    @RequestMapping("/submitUpdateConsumer")
    @ResponseBody
    public Object submitUpdateConsumer(@RequestBody User user){
        int count = userService.updateConsumer(user);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/addConsumer")
    @ResponseBody
    public Object addConsumer(@RequestBody User user){
        user.setRole("客户");
        int count = userService.addConsumer(user);
        System.out.println(count);
        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }
}
