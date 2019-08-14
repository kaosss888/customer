package com.application.passbook.customer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>库存请求响应</h1>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    /** 用户id */
    private Long userId;

    /** 优惠券模板信息，用户没有领取且没有过期的优惠券 */
    private List<PassTemplateInfo> passTemplateInfos;
}
