package com.application.passbook.customer.vo;

import com.application.passbook.customer.constant.FeedbackType;
import com.google.common.base.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    private Long userId;

    /** 评论类型 */
    private String type;

    /** PassTemplate的RowKey，如果是app类型则没有 */
    private String templateId;

    private String comment;

    public boolean validate() {

        FeedbackType feedbackType = Enums.getIfPresent(FeedbackType.class, this.type.toUpperCase()).orNull();

        return !(null == feedbackType || null == comment);
    }
}
