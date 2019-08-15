package com.application.passbook.customer.service;

import com.alibaba.fastjson.JSON;
import com.application.passbook.customer.constant.FeedbackType;
import com.application.passbook.customer.vo.Feedback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户反馈服务测试</h1>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedbackServiceTest extends AbstractServiceTest {

    @Autowired
    private IFeedbackService feedbackService;

    @Test
    public void testCreateFeedback() {

        Feedback appFeedback = new Feedback();
        appFeedback.setUserId(userId);
        appFeedback.setType(FeedbackType.APP.getCode());
        appFeedback.setTemplateId("-1");
        appFeedback.setComment("comment");

        System.out.println(JSON.toJSONString(feedbackService.createFeedback(appFeedback)));

        Feedback passFeedback = new Feedback();
        passFeedback.setUserId(userId);
        passFeedback.setType(FeedbackType.PASS.getCode());
        passFeedback.setTemplateId("");
        passFeedback.setComment("comment");

        System.out.println(JSON.toJSONString(feedbackService.createFeedback(passFeedback)));
    }

    @Test
    public void testGetFeedback() {
        System.out.println(JSON.toJSONString(feedbackService.getFeedback(userId)));
    }
}
