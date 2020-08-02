package uestc.team03.mall.service;

import uestc.team03.mall.common.domain.Product;

import java.util.List;

public interface ProductService {
    Product findProductById(String proId);

    Product findProductByName(String pname);

    List<Product> productList();
}
