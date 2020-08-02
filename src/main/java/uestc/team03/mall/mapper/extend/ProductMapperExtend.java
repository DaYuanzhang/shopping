package uestc.team03.mall.mapper.extend;

import uestc.team03.mall.common.domain.Product;

public interface ProductMapperExtend {
    Product selectByName(String pname);
}
