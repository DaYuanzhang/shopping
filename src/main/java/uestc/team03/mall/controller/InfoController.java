package uestc.team03.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.utils.Result;
import uestc.team03.mall.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class InfoController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/info")
    public String Person_info(ModelMap modelMap, HttpSession session){
        User user1= (User)session.getAttribute("user");
        modelMap.put("user",user1);
        return "/view/userinfo";
    }
    @RequestMapping("/pwdchange")
    public String PasswordChange(ModelMap modelMap, HttpSession session){
        User user2= (User)session.getAttribute("user");
        modelMap.put("user",user2);
        return "/view/pwdchange";
    }
    @RequestMapping("/infochange")
    public String InfoChange(ModelMap modelMap, HttpSession session){
        User user2= (User)session.getAttribute("user");
        modelMap.put("user",user2);
        return "/view/showConsumerUpdate";
    }
    @RequestMapping("/submitUpdateConsumer")
    @ResponseBody
    public Object submitUpdateConsumer(@RequestBody User user,HttpSession session){
        userMapper.updateByPrimaryKeySelective(user);
        User newuser=userMapper.selectByPrimaryKey(user.getId());
        session.setAttribute("user",newuser);
        return Result.success("操作成功",200);
    }
    @RequestMapping("/PwdChangeRequest")
    @ResponseBody
    public Object pwdchange(@RequestBody User user){
        User olduser=userMapper.selectByLoginname(user.getLoginname());
        if(olduser.getPassword().equals(user.getPassword())){
            olduser.setPassword(user.getNewpassword());
            System.out.println("更改成功");
            int count =userMapper.updateByPrimaryKeySelective(olduser);
            return Result.success(count,"更改成功",200);
        }else{
            System.out.println("更改失败");
            return Result.fail("shibai",200);
        }
    }



}


