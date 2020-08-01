package uestc.team03.mall.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.Order;
import uestc.team03.mall.common.domain.OrderExample;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.domain.UserExample;
import uestc.team03.mall.mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo findOrder(int pageNo,int pageSize,Order order)
    {
        PageHelper.startPage(pageNo,pageSize);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        /*if(order != null){
            if(order.getConsumer().getLoginname() != null && order.getConsumer().getLoginname().trim().length()>0){
                criteria.andLoginnameLike("%"+user.getLoginname()+"%");
            }
            if(user.getRemark() != null && user.getRemark().trim().length()>0){
                //System.out.println(user.getRemark());
                criteria.andRemarkLike("%"+user.getRemark()+"%");
            }
        }*/
        List<Order> orderList = orderMapper.orderList();
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public Order findOrderById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int removeOrder(Integer... ids) {
        int count = 0;
        if(ids != null && ids.length > 0){
            for(Integer id : ids){
                int i = orderMapper.deleteByPrimaryKey(id);
                count = count + i;
            }
        }
        return count;
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }
}
