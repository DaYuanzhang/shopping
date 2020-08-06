package uestc.team03.mall.common.domain.extend;

import uestc.team03.mall.common.domain.Product;
import uestc.team03.mall.common.domain.User;

import javax.jws.soap.SOAPBinding;

public class OrderExtend {
   private User consumer;
   private User merchant;
   private Product product;
   private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getConsumer() {
        return consumer;
    }

    public void setConsumer(User consumer) {
        this.consumer = consumer;
    }

    public User getMerchant() {
        return merchant;
    }

    public void setMerchant(User merchant) {
        this.merchant = merchant;
    }
}
