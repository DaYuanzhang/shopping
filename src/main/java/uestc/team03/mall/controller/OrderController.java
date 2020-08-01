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
import uestc.team03.mall.service.ProductService;
import uestc.team03.mall.service.UserService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/listOrder")
    @ResponseBody
    public Object listOrder(Order order,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        PageInfo<Order> pageInfo = orderService.findOrder(pageNo,pageSize,order);
        return Result.success(pageInfo);
    }

    @RequestMapping("/showOrder")
    public String showOrder(ModelMap modelMap, Integer id){
        Order order = orderService.findOrderById(id);
        order.setConsumer(userService.findUserById(order.getConId()));
        order.setMerchant(userService.findUserById(order.getMerId()));
        order.setProduct(productService.findProductById(order.getProId()));
        modelMap.put("order",order);
        return "/view/showOrderDetail";
    }

    @RequestMapping("/delOrder")
    @ResponseBody
    public Object delOrder(@RequestBody Integer[] ids){
        int count = orderService.removeOrder(ids);
        return Result.success(count,"操作成功",200);
    }
    @RequestMapping("/updOrder")
    public String updOrder(ModelMap modelMap,Integer id){
        Order order = orderService.findOrderById(id);
        order.setConsumer(userService.findUserById(order.getConId()));
        order.setMerchant(userService.findUserById(order.getMerId()));
        order.setProduct(productService.findProductById(order.getProId()));
        modelMap.put("order",order);
        return "/view/showOrderUpdat";
    }

    @RequestMapping("/submitUpdateOrder")
    @ResponseBody
    public Object submitUpdateOrder(@RequestBody Order order,String cname,String mname,String tel,Integer price,String pname){
        System.out.println(cname);
        System.out.println(mname);
        System.out.println(pname);
        int count = orderService.updateOrder(order);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(@RequestBody Order order){
        int count = orderService.addOrder(order);
        //System.out.println(count);
        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }
}
