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
import uestc.team03.mall.common.domain.Product;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.utils.Result;
import uestc.team03.mall.service.OrderService;
import uestc.team03.mall.service.ProductService;
import uestc.team03.mall.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public Object listOrder(Order order,String cloginname,String mloginname,String pname,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        PageInfo<Order> pageInfo = orderService.findOrder(pageNo,pageSize,order,cloginname,mloginname,pname);
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
        List<User> consumerList = userService.consumerList();
        List<User> merchantList = userService.merchantList();
        List<Product> productList = productService.productList();
       for(int i=0;i<consumerList.size();i++){
           if(consumerList.get(i).getLoginname().equals(order.getConsumer().getLoginname())){
               consumerList.remove(i);
               i--;
           }
       }
        for(int i=0;i<merchantList.size();i++){
            if(merchantList.get(i).getLoginname().equals(order.getMerchant().getLoginname())){
                merchantList.remove(i);
                i--;
            }
        }
        for(int i=0;i<consumerList.size();i++){
            if(productList.get(i).getPname().equals(order.getProduct().getPname())){
                productList.remove(i);
                i--;
            }
        }
        modelMap.put("order",order);
        modelMap.addAttribute("consumerList",consumerList);
        modelMap.addAttribute("merchantList",merchantList);
        modelMap.addAttribute("productList",productList);
        return "/view/showOrderUpdat";
    }

    @RequestMapping("/submitUpdateOrder")
    @ResponseBody
    public Object submitUpdateOrder(@RequestBody Order order,String cname,String pname,String mname){
        User consumer = userService.findUserByName(cname);
        User merchant = userService.findUserByName(mname);
        Product product = productService.findProductByName(pname);
        if(consumer==null || product==null || merchant==null) return Result.fail("找不到商品或客户或商家",200);
        order.setProId(product.getPid());
        order.setConId(consumer.getId());
        order.setMerId(merchant.getId());
        order.setProduct(product);
        order.setMerchant(merchant);
        order.setConsumer(consumer);
        int count = orderService.updateOrder(order);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public Object addOrder(@RequestBody Order order,String cname,String mname,String pname){
        if(cname.equals("null") || mname.equals("null") || pname.equals("null")){
            return Result.fail("失败",200);
        }

        Order order1 = new Order();
        order1.setDat(new Date());
        order1.setAddr(order.getAddr());
        order1.setConsumer(userService.findUserByName(cname));
        order1.setConId(order1.getConsumer().getId());
        order1.setMerchant(userService.findUserByName(mname));
        order1.setMerId(order1.getMerchant().getId());
        order1.setProduct(productService.findProductByName(pname));
        order1.setProId(order1.getProduct().getPid());
        int count = orderService.addOrder(order1);

        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/showAddOrder")
    public Object showAddOrder(ModelMap modelMap) {
        //System.out.println(count);
        List<User> consumerList = userService.consumerList();
        List<User> merchantList = userService.merchantList();
        List<Product> productList = productService.productList();
        modelMap.addAttribute("consumerList", consumerList);
        modelMap.addAttribute("merchantList", merchantList);
        modelMap.addAttribute("productList", productList);
        return "/view/showAddOrder";

    }

    @RequestMapping("/showBuyOrder")
    public Object showBuyOrder(ModelMap modelMap, HttpSession session,Integer sId,String pid) {
        //System.out.println(count);
        Product product = productService.findProductById(pid);
        User user2 = userService.findUserById(sId);
        User user1= (User)session.getAttribute("user");
        modelMap.addAttribute("consumer", user1);
        modelMap.addAttribute("merchant", user2);
        modelMap.addAttribute("product", product);
        return "/view/showBuyProduct";

    }

    @RequestMapping("/addProductOrder")
    @ResponseBody
    public Object addOrder(String cname,String mname,String pname,String addr){
        Order order1 = new Order();
        order1.setDat(new Date());
        order1.setAddr(addr);
        order1.setConsumer(userService.findUserByName(cname));
        order1.setConId(order1.getConsumer().getId());
        order1.setMerchant(userService.findUserByName(mname));
        order1.setMerId(order1.getMerchant().getId());
        order1.setProduct(productService.findProductByName(pname));
        order1.setProId(order1.getProduct().getPid());
        int count = orderService.addOrder(order1);

        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }

}
