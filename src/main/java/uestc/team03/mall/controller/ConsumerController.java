package uestc.team03.mall.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uestc.team03.mall.common.domain.Order;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.utils.Result;
import uestc.team03.mall.service.OrderService;
import uestc.team03.mall.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ConsumerController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("/listConsumer")
    @ResponseBody
    public Object listConsumer(User user, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10")int pageSize){
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


    @RequestMapping("/addConsumer")
    @ResponseBody
    public Object addConsumer(@RequestBody User user){
        user.setRole("客户");
        int count = userService.addConsumer(user);
        //System.out.println(count);
        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/consumerOrder-list")
    public String OrderListPage(ModelMap modelMap, HttpSession session){
        User user1= (User)session.getAttribute("user");
        modelMap.put("user",user1);
        return "/view/consumerOrder-list";
    }

    @RequestMapping("/consumerlistOrder")
    @ResponseBody
    public Object Orderlist(ModelMap modelMap, HttpSession session,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize,String mloginname,String pname){
        User user1= (User)session.getAttribute("user");
        modelMap.put("user",user1);
        PageInfo<Order> pageInfo = orderService.consumerfindOrder(pageNo,pageSize,user1.getLoginname(),mloginname,pname);
        return Result.success(pageInfo);
    }

}
