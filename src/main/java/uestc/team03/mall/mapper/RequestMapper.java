package uestc.team03.mall.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uestc.team03.mall.common.domain.Request;
import uestc.team03.mall.common.domain.RequestExample;

public interface RequestMapper {
    long countByExample(RequestExample example);

    int deleteByExample(RequestExample example);

    int deleteByPrimaryKey(Integer reqNumber);

    int insert(Request record);

    int insertSelective(Request record);

    List<Request> selectByExample(RequestExample example);

    Request selectByPrimaryKey(Integer reqNumber);

    int updateByExampleSelective(@Param("record") Request record, @Param("example") RequestExample example);

    int updateByExample(@Param("record") Request record, @Param("example") RequestExample example);

    int updateByPrimaryKeySelective(Request record);

    int updateByPrimaryKey(Request record);
}