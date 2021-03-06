package uestc.team03.mall.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.Order;

import java.util.List;

public interface OrderService {
    public PageInfo<Order> findOrder(int pageNo, int pageSize, Order order, String cloginname, String mloginname, String pname);

    Order findOrderById(Integer id);

    int removeOrder(Integer[] ids);

    int updateOrder(Order order);

    int addOrder(Order order);

    PageInfo<Order> consumerfindOrder(int pageNo, int pageSize, String cloginname, String mloginname, String pname);

    PageInfo<Order> merchantfindOrder(int pageNo, int pageSize, String cloginname, String mloginname, String pname);
}
