package uestc.team03.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.*;
import uestc.team03.mall.mapper.ProductMapper;
import uestc.team03.mall.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<User> findConsumer(int pageNo, int pageSize,User user) {
        PageHelper.startPage(pageNo,pageSize);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(user != null){
            if(user.getLoginname() != null && user.getLoginname().trim().length()>0){
                criteria.andLoginnameLike("%"+user.getLoginname()+"%");
            }
            if(user.getRemark() != null && user.getRemark().trim().length()>0){
                //System.out.println(user.getRemark());
                criteria.andRemarkLike("%"+user.getRemark()+"%");
            }
        }
        criteria.andRoleEqualTo("客户");
        List<User> useList = userMapper.selectByExample(userExample);

        Collections.sort(useList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //升序
                return o1.getId().compareTo(o2.getId());
            }
        });

        PageInfo<User> pageInfo = new PageInfo<>(useList);
        return pageInfo;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int removeUser(Integer... ids) {
        int count = 0;
        if(ids != null && ids.length > 0){
            for(Integer id : ids){
                int i = userMapper.deleteByPrimaryKey(id);
                count = count + i;
            }
        }
        return count;
    }

    @Override
    public int updateConsumer(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addConsumer(User user) {
        List<User> users = new ArrayList<User>();
        UserExample userExample = new UserExample();
        users = userMapper.selectByExample(userExample);
        for(User user1:users){
            if(user.getLoginname().equals(user1.getLoginname())){
                return 0;
            }
        }
        return userMapper.insert(user);
    }

    @Override
    public PageInfo<User> findMerchant(int pageNo, int pageSize, User user) {
        PageHelper.startPage(pageNo,pageSize);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(user != null){
            if(user.getLoginname() != null && user.getLoginname().trim().length()>0){
                criteria.andLoginnameLike("%"+user.getLoginname()+"%");
            }
            if(user.getRemark() != null && user.getRemark().trim().length()>0){
                //System.out.println(user.getRemark());
                criteria.andRemarkLike("%"+user.getRemark()+"%");
            }
        }
        criteria.andRoleEqualTo("商家");
        List<User> useList = userMapper.selectByExample(userExample);

        Collections.sort(useList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //升序
                return o1.getId().compareTo(o2.getId());
            }
        });

        PageInfo<User> pageInfo = new PageInfo<>(useList);
        return pageInfo;
    }

    @Override
    public User login(User user){
        UserExample userexample = new UserExample();
        userexample.createCriteria().andLoginnameEqualTo(user.getLoginname());
        List<User> userList = userMapper.selectByExample(userexample);
        return userList.size()>0?userList.get(0):null;
    }

    @Override
    public User findUserByName(String cname) {
       User user = userMapper.selectByLoginname(cname);
       if(user==null) return null;
       return user;
    }

    @Override
    public List<User> consumerList() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoleEqualTo("客户");
        return  userMapper.selectByExample(userExample);
    }

    @Override
    public List<User> merchantList() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoleEqualTo("商家");
        return  userMapper.selectByExample(userExample);
    }

}
