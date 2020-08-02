package uestc.team03.mall.service;

import com.github.pagehelper.PageInfo;
import uestc.team03.mall.common.domain.Product;
import uestc.team03.mall.common.domain.User;

import java.util.List;

public interface UserService {
    public PageInfo<User> findConsumer(int pageNo, int pageSize,User user);

    User findUserById(Integer id);

    int removeUser(Integer... ids);

    int updateConsumer(User user);

    int addConsumer(User user);

    public PageInfo<User> findMerchant(int pageNo, int pageSize, User user);

    public User login(User user);

    User findUserByName(String cname);

    List<User> consumerList();

    List<User> merchantList();


}
