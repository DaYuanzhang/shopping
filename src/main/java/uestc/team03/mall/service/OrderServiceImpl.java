package uestc.team03.mall.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.*;
import uestc.team03.mall.mapper.OrderMapper;
import uestc.team03.mall.mapper.ProductMapper;
import uestc.team03.mall.mapper.UserMapper;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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


        PageHelper.startPage(pageNo,pageSize);
        List<Order> orderList = orderMapper.orderList();

        if(mloginname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getMerchant().getLoginname().contains(mloginname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        if(cloginname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getConsumer().getLoginname().contains(cloginname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        if(pname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getProduct().getPname().contains(pname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        for (Order order1:orderList){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            order1.setDate(sdf.format(order1.getDat()));
        }

        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                //升序
                return o1.getOid().compareTo(o2.getOid());
            }
        });

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

    @Override
    public PageInfo<Order> consumerfindOrder(int pageNo, int pageSize, String cloginname, String mloginname, String pname) {
        if(mloginname!=null && mloginname.trim().length()==0) mloginname=null;
        if(pname!=null && pname.trim().length()==0) pname=null;


        PageHelper.startPage(pageNo,pageSize);
        List<Order> orderList = orderMapper.orderList();

        if(mloginname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getMerchant().getLoginname().contains(mloginname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        if(cloginname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getConsumer().getLoginname().equals(cloginname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        if(pname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getProduct().getPname().contains(pname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        for (Order order1:orderList){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            order1.setDate(sdf.format(order1.getDat()));
        }
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                //升序
                return o1.getOid().compareTo(o2.getOid());
            }
        });

        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public PageInfo<Order> merchantfindOrder(int pageNo, int pageSize, String cloginname, String mloginname, String pname) {
        if(cloginname!=null && cloginname.trim().length()==0) cloginname=null;
        if(pname!=null && pname.trim().length()==0) pname=null;


        PageHelper.startPage(pageNo,pageSize);
        List<Order> orderList = orderMapper.orderList();

        if(mloginname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getMerchant().getLoginname().equals(mloginname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        if(cloginname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getConsumer().getLoginname().contains(cloginname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }
        if(pname != null ){
            for(int i=0;i<orderList.size();i++){
                if (orderList.get(i).getProduct().getPname().contains(pname)==false){
                    orderList.remove(i);
                    i--;
                }
            }
        }

        for (Order order1:orderList){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            order1.setDate(sdf.format(order1.getDat()));
        }

        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                //升序
                return o1.getOid().compareTo(o2.getOid());
            }
        });

        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }


}
