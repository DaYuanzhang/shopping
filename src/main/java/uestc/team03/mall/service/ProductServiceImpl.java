package uestc.team03.mall.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<Product> findProduct(int pageNo, int pageSize, Product product, String mloginname) {
        String pname = product.getPname();
        if(mloginname!=null && mloginname.trim().length()==0) mloginname=null;
        if(product.getPname()!=null && product.getPname().trim().length()==0) pname=null;
        PageHelper.startPage(pageNo,pageSize);
        List<Product> productList = productMapper.productList();
        if(mloginname != null ){
            for(int i=0;i<productList.size();i++){
                if (productList.get(i).getMerchant().getLoginname().contains(mloginname)==false){
                    productList.remove(i);
                    i--;
                }
            }
        }
        if(pname != null){
            for (int i=0;i<productList.size();i++){
                if (productList.get(i).getPname().contains(pname) == false){
                    productList.remove(i);
                    i--;
                }
            }
        }
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    @Override
    public int removeProduct(String... ids) {
        int count = 0;
        if(ids != null && ids.length > 0){
            for(String id : ids){
                int i = productMapper.deleteByPrimaryKey(id);
                count = count + i;
            }
        }
        return count;
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

}
