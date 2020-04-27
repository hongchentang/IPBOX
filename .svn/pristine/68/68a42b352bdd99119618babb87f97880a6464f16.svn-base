package com.hcis.ipr.intellectual.cost.service.impl;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipr.intellectual.cost.dao.WarnTimeLineDao;
import com.hcis.ipr.intellectual.cost.entity.WarnTimeLine;
import com.hcis.ipr.intellectual.cost.service.PatentCostMonitorService;
import com.hcis.ipr.intellectual.cost.service.WarnTimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zhw
 * @date 2019/8/29
 **/
@Service
public class WarnTimeLineServiceImpl extends BaseServiceImpl<WarnTimeLine> implements WarnTimeLineService {

    @Autowired
    private WarnTimeLineDao warnTimeLineDao;

    @Autowired
    private PatentCostMonitorService patentCostMonitorService;

    @Override
    public MyBatisDao getBaseDao() {
        return warnTimeLineDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(WarnTimeLine warnTimeLine, String userId) {
        //修改配置
        warnTimeLine.setUpdatedby(userId);
        warnTimeLine.setUpdateTime(new Date());
        warnTimeLineDao.updateTimeLine(warnTimeLine);

        //更新监控数据
        patentCostMonitorService.monitorRefresh(warnTimeLine.getCompanyId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WarnTimeLine getByCompanyId(String companyId) {
        WarnTimeLine warnTimeLine =  warnTimeLineDao.getByCompanyId(companyId);
        if(warnTimeLine == null){
            WarnTimeLine insert = new WarnTimeLine();
            insert.setId(UUIDUtils.getUUId());
            insert.setCompanyId(companyId);
            insert.setOneTimeLine(300);
            insert.setTwoTimeLine(300);
            insert.setThreeTimeLine(10);
            insert.setDefaultValue();

            warnTimeLineDao.insert(insert);
            return insert;
        }
        return warnTimeLine;
    }
}
