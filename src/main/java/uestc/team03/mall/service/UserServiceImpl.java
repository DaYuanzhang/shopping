package uestc.team03.mall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.domain.UserExample;
import uestc.team03.mall.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
        PageInfo<User> pageInfo = new PageInfo<>(useList);
        return pageInfo;
    }
}
