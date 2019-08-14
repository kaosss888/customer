package com.application.passbook.customer.service;

import com.application.passbook.customer.vo.Feedback;
import com.application.passbook.customer.vo.Response;

import java.util.List;

/**
 * <h1>用户评论相关功能实现</h1>
 */
public interface IFeedbackService {

    /**
     * <h2>创建评论</h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    Response<Feedback> createFeedback(Feedback feedback);

    /**
     * <h2>获取用户评论</h2>
     * @param userId 用户id
     * @return {@link Response}
     */
    Response<List<Feedback>> getFeedback(Long userId);
}
