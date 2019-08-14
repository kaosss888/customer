package com.application.passbook.customer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>统一响应对象</h1>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /** 错误码：正确返回0 */
    private Integer errorCode = 0;

    /** 错误信息， 正确返回为空 */
    private String errorMessage = "";

    private T data;

    public Response(T data) {
        this.data = data;
    }

    /**
     * <h2>空响应</h2>
     * @return
     */
    public static Response success() {
        return new Response();
    }

    /**
     * <h2>错误响应</h2>
     * @param errorMessage
     * @return
     */
    public static Response failure(String errorMessage) {
        return new Response(-1, errorMessage, null);
    }
}
