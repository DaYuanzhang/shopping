package uestc.team03.mall.mapper.extend;

import uestc.team03.mall.common.domain.User;

import java.util.List;

public interface UserMapperExtend {
    User selectByLoginname(String loginname);

}
