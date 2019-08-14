package com.application.passbook.customer.service;

import com.application.passbook.customer.vo.Response;

/**
 * <h1>获取库存信息，返回用户没有领取的，即库存功能实现接口</h1>
 */
public interface IInventoryService {

    /**
     * <h2>获取库存信息</h2>
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    Response getInventoryInfo(Long userId) throws Exception;
}
