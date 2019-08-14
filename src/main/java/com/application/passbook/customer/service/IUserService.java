package com.application.passbook.customer.service;

import com.application.passbook.customer.vo.Response;
import com.application.passbook.customer.vo.User;

/**
 * <h1>创建用户服务</h1>
 */
public interface IUserService {

    /**
     * <h2>创建用户</h2>
     * @param user {@link User}
     * @return {@link Response}
     * @throws Exception
     */
    Response<User> createUser(User user) throws Exception;
}
