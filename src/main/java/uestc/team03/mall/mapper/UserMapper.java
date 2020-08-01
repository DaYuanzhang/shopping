package uestc.team03.mall.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.common.domain.UserExample;
import uestc.team03.mall.mapper.extend.UserMapperExtend;

public interface UserMapper extends UserMapperExtend {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}