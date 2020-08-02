package uestc.team03.mall.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.team03.mall.common.domain.Order;
import uestc.team03.mall.common.domain.Product;
import uestc.team03.mall.common.domain.ProductExample;
import uestc.team03.mall.mapper.ProductMapper;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findProductById(String proId) {
        return productMapper.selectByPrimaryKey(proId);
    }

    @Override
    public Product findProductByName(String pname) {
            Product product = productMapper.selectByName(pname);
            if(product==null){
                return null;
            }
        return product;
    }

    @Override
    public List<Product> productList() {
        ProductExample productExample = new ProductExample();
        return productMapper.selectByExample(productExample);
    }

}
