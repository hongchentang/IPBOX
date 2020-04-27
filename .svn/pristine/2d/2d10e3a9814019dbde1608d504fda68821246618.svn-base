package com.hcis.ipr.intellectual.cost.service;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.intellectual.cost.entity.WarnTimeLine;


/**
 * @author zhw
 */
public interface WarnTimeLineService extends IBaseService<WarnTimeLine> {

    /**
     *
     * @param warnTimeLine
     * @param userId
     */
    void updateById(WarnTimeLine warnTimeLine, String userId);

    /**
     * 根据公司id获取监控设置
     * @param companyId
     * @return
     */
    WarnTimeLine getByCompanyId(String companyId);
}
