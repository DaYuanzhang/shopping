package uestc.team03.mall.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import uestc.team03.mall.common.domain.Product;
import uestc.team03.mall.common.domain.ProductExample;
import uestc.team03.mall.mapper.extend.ProductMapperExtend;

public interface ProductMapper extends ProductMapperExtend {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(String pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}