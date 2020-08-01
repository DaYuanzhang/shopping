package uestc.team03.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.utils.Result;
import uestc.team03.mall.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/loginrequest")
    @ResponseBody
    public Object login(@RequestBody User user, HttpSession session){
        User userInDB =userService.login(user);
        if(userInDB==null){
            return Result.fail("用户名不存在",200);
        }else {
            if(userInDB.getPassword().equals(user.getPassword())){
                session.setAttribute("user",userInDB);
                return Result.success("登录成功",200);
            }else {
                return  Result.fail("登录失败",200);
            }
        }
    }
    @RequestMapping("/regrequest")
    @ResponseBody
    public Object reg(@RequestBody User user){
        user.setRole("客户");
        int count = userService.addConsumer(user);
        //System.out.println(count);
        if(count==0){
            return Result.fail("注册失败，该用户已存在！",200);
        }
        return Result.success(count,"注册成功",200);
    }

}
