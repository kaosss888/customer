package com.application.passbook.customer.service;

import com.application.passbook.customer.vo.PassTemplate;

/**
 * <h1>Pass HBase Service</h1>
 */
public interface IHBasePassService {

    /**
     * <h2>将PassTemplate写入HBase</h2>
     * @param passTemplate {@link PassTemplate}
     * @return
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
