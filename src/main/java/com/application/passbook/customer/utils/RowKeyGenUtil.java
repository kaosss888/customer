package com.application.passbook.customer.utils;

import com.application.passbook.customer.vo.Feedback;
import com.application.passbook.customer.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>RowKey生成器工具类</h1>
 */
@Slf4j
public class RowKeyGenUtil {

    /**
     * <h2>根据提供的PassTemplate对象生成RowKey</h2>
     * @param passTemplate {@link PassTemplate}
     * @return String RowKey
     */
    public static String genPassTemplateRowKey(PassTemplate passTemplate) {

        String passInfo = String.valueOf(passTemplate.getId() + "_" + passTemplate.getTitle());
        String rowKey = DigestUtils.md5Hex(passInfo); // 利用MD5分散数据
        log.info("GenPassTemplateRowKey: {}, {}", passInfo, rowKey);

        return rowKey;
    }

    /**
     * <h2>根据Feedback构造RowKey</h2>
     * @param feedback {@link Feedback}
     * @return String RowKey
     */
    public static String genFeedbackRowKey(Feedback feedback) {

        /**
         * 生成RowKey采用两部分
         * 1、前一部分利用用户ID，同一用户feedback会更加聚集，
         * 做翻转是为了让数据分散，当用户数量增加，userId重复度增加，数据会变得更加集中，不利于负载均衡
         * 2、后一部分加时间戳，当用户feedback越晚，值越小，数据越靠前，方便做scan
         */
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString() +
                (Long.MAX_VALUE - System.currentTimeMillis());
    }
}
