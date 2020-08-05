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
import uestc.team03.mall.mapper.ProductMapper;
import uestc.team03.mall.service.ProductService;
import uestc.team03.mall.service.UserService;
import uestc.team03.mall.service.OrderService;

import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @RequestMapping("/adminlistProduct")
    @ResponseBody
    public Object listProduct(Product product, String mloginname, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        PageInfo<Product> pageInfo = productService.findProduct(pageNo,pageSize,product,mloginname);
        return Result.success(pageInfo);
    }

    @RequestMapping("/showProduct")
    public String showOrder(ModelMap modelMap, String  id){
        Product product = productService.findProductById(id);
        product.setMerchant(userService.findUserById(product.getsId()));
        modelMap.put("product",product);
        return "/view/showProductDetail.html";
    }

    @RequestMapping("/delProduct")
    @ResponseBody
    public Object delOrder(@RequestBody String... ids){
        int count = productService.removeProduct(ids);
        return Result.success(count,"操作成功",200);
    }


    @RequestMapping("/updProduct")
    public String updProduct(ModelMap modelMap,String id){
        Product product = productService.findProductById(id);
        product.setMerchant(userService.findUserById(product.getsId()));
        List<User> merchantList = userService.merchantList();
        for(int i=0;i<merchantList.size();i++){
            if(merchantList.get(i).getLoginname().equals(product.getMerchant().getLoginname())){
                merchantList.remove(i);
                i--;
            }
        }
        modelMap.put("product",product);
        modelMap.addAttribute("merchantList",merchantList);
        return "/view/showProductUpdat.html";
    }


    @RequestMapping("/submitUpdateProduct")
    @ResponseBody
    public Object submitUpdateProduct(@RequestBody Product product,String mname){
        User merchant = userService.findUserByName(mname);
        if(merchant==null) return Result.fail("找不到商品或客户或商家",200);
        product.setsId(merchant.getId());
        product.setMerchant(merchant);
        int count = productService.updateProduct(product);
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/addProduct")
    @ResponseBody
    public Object addProduct(@RequestBody Product product,String mname){
        if(mname.equals("null")){
            return Result.fail("失败",200);
        }
        List<Product> products = productMapper.productList();
        if(product.getPid()==null){
            product.setPid("p"+products.size());
        }
        product.setMerchant(userService.findUserByName(mname));
        product.setsId(product.getMerchant().getId());
        int count = productService.addProduct(product);

        if(count==0){
            return Result.fail("操作失败，该用户已存在！",200);
        }
        return Result.success(count,"操作成功",200);
    }

    @RequestMapping("/showAddProduct")
    public Object showAddProduct(ModelMap modelMap) {
        //System.out.println(count);
        List<User> merchantList = userService.merchantList();
        modelMap.addAttribute("merchantList", merchantList);
        return "/view/showAddProduct.html";

    }

}
