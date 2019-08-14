package com.application.passbook.customer.service;

import com.application.passbook.customer.vo.GainPassTemplateRequest;
import com.application.passbook.customer.vo.Response;

/**
 * <h1>用户领取优惠券功能实现</h1>
 */
public interface IGainPassTemplateService {

    /**
     * <h2>用户领取优惠券</h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;
}
