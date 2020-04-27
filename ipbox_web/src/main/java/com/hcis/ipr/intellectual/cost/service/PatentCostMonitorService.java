package com.hcis.ipr.intellectual.cost.service;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.cost.dto.PatentCostDetailDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostListDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.entity.PatentCostMonitor;
import com.hcis.ipr.intellectual.cost.entity.PatentCostPayment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhw
 */
public interface PatentCostMonitorService extends IBaseService<PatentCostMonitor> {

    /**
     * 刷新监控数据
     * @param companyId
     */
    void monitorRefresh(String companyId);

    /**
     * 列表
     * @param searchParam
     * @return
     */
    List<PatentCostMonitorListDto> getList(SearchParam searchParam);

    /**
     * 查询需邮件提醒的数据
     * @param max
     * @param min
     * @return
     */
    List<PatentCostMonitorDto> findEmails(Integer max, Integer min, Integer costType);
}
