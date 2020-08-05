package uestc.team03.mall.service;

import com.github.pagehelper.PageInfo;
import uestc.team03.mall.common.domain.Order;
import uestc.team03.mall.common.domain.Product;

import java.util.List;

public interface ProductService {
    Product findProductById(String proId);

    Product findProductByName(String pname);

    List<Product> productList();

    PageInfo<Product> findProduct(int pageNo, int pageSize, Product product, String mloginname);

    int removeProduct(String... ids);

    int updateProduct(Product product);

    int addProduct(Product product);
}
