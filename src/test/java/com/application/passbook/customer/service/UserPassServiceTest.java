package com.application.passbook.customer.service;

import com.alibaba.fastjson.JSON;
import com.application.passbook.customer.vo.Pass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户优惠券服务测试</h1>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPassServiceTest extends AbstractServiceTest {

    @Autowired
    private IUserPassService userPassService;

    @Test
    public void testGetUserPassInfo() throws Exception {
        System.out.println(JSON.toJSONString(userPassService.getUserPassInfo(userId)));
    }

    @Test
    public void testGetUserUsedPassInfo() throws Exception {
        System.out.println(JSON.toJSONString(userPassService.getUserUsedPassInfo(userId)));
    }

    @Test
    public void testGetUserAllPassInfo() throws Exception {
        System.out.println(JSON.toJSONString(userPassService.getUserAllPassInfo(userId)));
    }

    @Test
    public void testUserUsePass() {
        Pass pass = new Pass();
        pass.setUserId(userId);
        pass.setTemplateId("");

        System.out.println(JSON.toJSONString(userPassService.userUsePass(pass)));
    }
}
