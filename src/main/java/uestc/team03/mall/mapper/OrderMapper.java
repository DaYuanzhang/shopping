package uestc.team03.mall.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uestc.team03.mall.common.domain.Order;
import uestc.team03.mall.common.domain.OrderExample;
import uestc.team03.mall.mapper.extend.OrderMapperExtend;

public interface OrderMapper extends OrderMapperExtend {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}