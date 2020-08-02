package uestc.team03.mall.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.*;
import uestc.team03.mall.mapper.OrderMapper;
import uestc.team03.mall.mapper.ProductMapper;
import uestc.team03.mall.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo findOrder(int pageNo,int pageSize,Order order,String cloginname,String mloginname,String pname)
    {
        if(cloginname!=null && cloginname.trim().length()==0) cloginname=null;
        if(mloginname!=null && mloginname.trim().length()==0) mloginname=null;
        if(pname!=null && pname.trim().length()==0) pname=null;

        System.out.println("findOrder loginname="+cloginname);
        PageHelper.startPage(pageNo,pageSize);
        List<Order> orderList = orderMapper.orderList();
        int count = -1;
        if(cloginname == null && mloginname == null && pname == null) count = 0;
        else if(cloginname != null && mloginname == null && pname == null) count = 1;
        else if (cloginname == null && mloginname != null && pname == null) count = 2;
        else if (cloginname == null && mloginname == null && pname != null) count = 3;
        else if(cloginname != null && mloginname != null && pname == null) count = 4;
        else if(cloginname != null && mloginname == null && pname != null) count = 5;
        else if(cloginname == null && mloginname != null && pname != null) count = 6;
        else count = 7;

        switch (count){
            case 0: break;
            case 1: orderList = this.findOrder(cloginname,orderList);
                    break;
            case 2: orderList = this.findOrder2(mloginname,orderList);
                    break;
            case 3: orderList = this.findOrder3(pname,orderList);
                    break;
            case 4: orderList = this.findOrder(cloginname,mloginname,orderList);
                    break;
            case 5: orderList = this.findOrder2(cloginname,pname,orderList);
                    break;
            case 6: orderList = this.findOrder3(mloginname,pname,orderList);
                    break;
            default: orderList = this.findOrder(cloginname,mloginname,pname,orderList);
                    break;
        }
                PageInfo<Order> pageInfo = new PageInfo<>(orderList);
                return pageInfo;
    }

    private List<Order> findOrder(String cloginname, String mloginname, String pname, List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getConsumer().getLoginname().contains(cloginname)==false || orders.get(i).getMerchant().getLoginname().contains(mloginname) || orders.get(i).getProduct().getPname().contains(pname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }

    private List<Order> findOrder3(String mloginname, String pname, List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getMerchant().getLoginname().contains(mloginname)==false || orders.get(i).getProduct().getPname().contains(pname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }

    private List<Order> findOrder2(String cloginname, String pname, List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getConsumer().getLoginname().contains(cloginname)==false || orders.get(i).getProduct().getPname().contains(pname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }

    private List<Order> findOrder(String cloginname, String mloginname, List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getConsumer().getLoginname().contains(cloginname)==false || orders.get(i).getMerchant().getLoginname().contains(mloginname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;

    }

    private List<Order> findOrder3(String pname, List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getProduct().getPname().contains(pname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }

    private List<Order> findOrder(String cloginname,List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getConsumer().getLoginname().contains(cloginname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;
    }

    private List<Order> findOrder2(String mloginname,List<Order> orders) {
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getMerchant().getLoginname().contains(mloginname)==false){
                orders.remove(i);
                i--;
            }
        }
        return orders;
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
