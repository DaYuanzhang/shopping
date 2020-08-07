package uestc.team03.mall.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import uestc.team03.mall.common.domain.Request;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.utils.Result;
import uestc.team03.mall.mapper.UserMapper;
import uestc.team03.mall.service.RequestService;
import uestc.team03.mall.service.UserService;

import java.util.Arrays;

@Controller
public class AdminController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RequestService  requestService;

    @RequestMapping("/listRoleRequest")
    @ResponseBody
    public Object listRoleRequest(Request request, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10")int pageSize) {
        PageInfo<Request> pageInfo = requestService.findRequest(pageNo, pageSize, request);
        return Result.success(pageInfo);
    }

    @RequestMapping("/agreeRequest")
    @ResponseBody
    public Object agreeRequest(@RequestBody Integer[] ids){
        int i = 0;
        for(; i < ids.length; i++){
            if(i%2 == 0){
                User user = new User();
                user.setId(ids[i]);
                user.setRole("商家");

                userMapper.updateByPrimaryKeySelective(user);
            }
            else
                requestService.deleteRequest(ids[i]);
        }

        return Result.success(i/2,"操作成功",200);
    }

    @RequestMapping("/opposeRequest")
    @ResponseBody
    public Object opposeRequest(@RequestBody Integer[] ids){
        int i = 0;
        for(; i < ids.length; i++){
            requestService.deleteRequest(ids[i]);
        }

        return Result.success(i,"操作成功",200);
    }
}
