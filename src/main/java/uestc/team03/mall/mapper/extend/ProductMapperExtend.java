package uestc.team03.mall.mapper.extend;

import uestc.team03.mall.common.domain.Product;

import java.util.List;

public interface ProductMapperExtend {
    Product selectByName(String pname);

    List<Product> productList();
}
