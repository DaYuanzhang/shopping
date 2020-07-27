package uestc.team03.mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uestc.team03.mall.common.domain.User;
import uestc.team03.mall.mapper.UserMapper;

@SpringBootTest
class MallApplicationTests {

    @Autowired
    UserMapper userMapper;


    @Test
    void contextLoads() {
        for(int i = 1;i<50;i++){
            User user = new User();
            if(i%2==0){
                user.setLoginname("consumer"+i);
                user.setPassword("consumer"+i);
                user.setRemark("consumer remark "+i);
                user.setRole("客户");
            }else if(i%2==1){
                user.setLoginname("merchant"+i);
                user.setPassword("merchant"+i);
                user.setRemark("merchant remark "+i);
                user.setRole("商家");
            }
            userMapper.insert(user);

        }
    }

}
