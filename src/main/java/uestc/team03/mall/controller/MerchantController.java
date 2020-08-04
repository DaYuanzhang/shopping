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

@Controller
public class MerchantController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/listMerchant")
    @ResponseBody
    public Object listMerchant(User user, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10")int pageSize){
        PageInfo<User> pageInfo = userService.findMerchant(pageNo, pageSize,user);
        return Result.success(pageInfo);
    }

    @RequestMapping("/showMerchant")
    public String showMerchant(ModelMap modelMap, Integer id){
        User user = userService.findUserById(id);
        modelMap.put("user",user);
        return "/view/showConsumerDetail";
    }

    @RequestMapping("/delMerchant")
    @ResponseBody
    public Object delMerchant(@RequestBody Integer[] ids){
        int count = userService.removeUser(ids);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/updMerchant")
    public String updMerchant(ModelMap modelMap,Integer id){
        User user = userService.findUserById(id);
        modelMap.put("user",user);
        return "/view/showConsumerUpdate";
    }

    @RequestMapping("/submitUpdateMerchant")
    @ResponseBody
    public Object submitUpdateConsumer(@RequestBody User user){
        int count = userService.updateConsumer(user);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/addMerchant")
    @ResponseBody
    public Object addConsumer(@RequestBody User user){
        user.setRole("商家");
        int count = userService.addConsumer(user);
        //System.out.println(count);
        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }


    @RequestMapping("/merchantOrder-list")
    public String OrderListPage(ModelMap modelMap, HttpSession session){
        User user1= (User)session.getAttribute("user");
        modelMap.put("user",user1);
        return "/view/consumerOrder-list";
    }

    @RequestMapping("/merchantlistOrder")
    @ResponseBody
    public Object Orderlist(ModelMap modelMap, HttpSession session, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, String cloginname, String pname){
        User user1= (User)session.getAttribute("user");
        System.out.println(user1.getLoginname());
        modelMap.put("user",user1);
        PageInfo<Order> pageInfo = orderService.findOrder(pageNo,pageSize,null,cloginname,user1.getLoginname(),pname);
        return Result.success(pageInfo);
    }




}
