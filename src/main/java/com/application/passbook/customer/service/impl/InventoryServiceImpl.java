package com.application.passbook.customer.service.impl;

import com.application.passbook.customer.constant.Constants;
import com.application.passbook.customer.dao.MerchantsDao;
import com.application.passbook.customer.entity.Merchants;
import com.application.passbook.customer.mapper.PassTemplateRowMapper;
import com.application.passbook.customer.service.IInventoryService;
import com.application.passbook.customer.service.IUserPassService;
import com.application.passbook.customer.utils.RowKeyGenUtil;
import com.application.passbook.customer.vo.*;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.LongComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>获取库存信息</h1>
 */
@Slf4j
@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private MerchantsDao merchantsDao;

    @Autowired
    private IUserPassService userPassService;

    @Override
    public Response<InventoryResponse> getInventoryInfo(Long userId) throws Exception {

        Response<List<PassInfo>> allUserPass = userPassService.getUserAllPassInfo(userId);
        List<PassInfo> passInfos = allUserPass.getData();

        List<PassTemplate> excludeObject = passInfos.stream()
                .map(
                        PassInfo::getPassTemplate
                ).collect(Collectors.toList());
        List<String> excludeIds = new ArrayList<>();
        excludeObject.forEach(e -> excludeIds.add(RowKeyGenUtil.genPassTemplateRowKey(e)));

        return new Response<>(new InventoryResponse(
                userId, buildPassTemplateInfo(getAvailablePassTemplate(excludeIds))));
    }

    /**
     * <h2>获取系统中可用的优惠券</h2>
     * @param excludeIds 需要排除的优惠券Ids
     * @return {@link PassTemplate}
     */
    private List<PassTemplate> getAvailablePassTemplate(List<String> excludeIds) {

        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);

        filterList.addFilter(
                new SingleColumnValueFilter(
                        Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                        Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                        CompareFilter.CompareOp.GREATER,
                        new LongComparator(0L)
                )
        );

        filterList.addFilter(
                new SingleColumnValueFilter(
                        Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C),
                        Bytes.toBytes(Constants.PassTemplateTable.LIMIT),
                        CompareFilter.CompareOp.EQUAL,
                        Bytes.toBytes("-1")
                )
        );

        Scan scan = new Scan();
        scan.setFilter(filterList);

        List<PassTemplate> validTemplates = hbaseTemplate.find(
                Constants.PassTemplateTable.TABLE_NAME,
                scan,
                new PassTemplateRowMapper()
        );
        List<PassTemplate> availablePassTemplates = new ArrayList<>();

        Date cur = new Date();

        for (PassTemplate validTemplate : validTemplates) {
            if (excludeIds.contains(RowKeyGenUtil.genPassTemplateRowKey(validTemplate))) {
                continue;
            }

            if (cur.getTime() >= validTemplate.getStart().getTime()
                    && cur.getTime() <= validTemplate.getEnd().getTime()) {
                availablePassTemplates.add(validTemplate);
            }
        }

        return availablePassTemplates;
    }

    /**
     * <h2>构造优惠券的信息</h2>
     * @param passTemplates {@link PassTemplate}
     * @return {@link PassTemplateInfo}
     */
    private List<PassTemplateInfo> buildPassTemplateInfo(List<PassTemplate> passTemplates) {

        Map<Integer, Merchants> merchantsMap = new HashMap<>();
        List<Integer> merchantsIds = passTemplates.stream().map(PassTemplate::getId).collect(Collectors.toList());
        List<Merchants> merchants = merchantsDao.findByIdIn(merchantsIds);
        merchants.forEach(m -> merchantsMap.put(m.getId(), m));

        List<PassTemplateInfo> result = new ArrayList<>(passTemplates.size());

        for (PassTemplate item : passTemplates) {
            Merchants mc = merchantsMap.getOrDefault(item.getId(), null);

            if (null == mc) {
                log.error("Merchants Error: {}", item.getId());
                continue;
            }

            result.add(new PassTemplateInfo(item, mc));
        }

        return result;
    }
}
