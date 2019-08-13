package com.application.passbook.customer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pass {

    private Long userId;

    /** pass在HBase中的RowKey */
    private String rowKey;

    /** PassTemplate在HBase中的RowKey */
    private String templateId;

    /** 优惠券token，有可能是null，则填充-1 */
    private String token;

    /** 领取日期 */
    private Date assignedDate;

    /** 消费日期 */
    private Date conDate;
}
