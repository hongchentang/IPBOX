package com.hcis.ipr.intellectual.cost.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.cost.dto.PatentCostDetailDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostListDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCostMonitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhw
 */
@Repository("patentCostMonitorDao")
public class PatentCostMonitorDao extends MyBatisDao {

    public void batchInsert(List<PatentCostMonitor> list) {
        this.getSqlSession().selectList(namespace + ".insertBatch", list);
    }

    public List<PatentCostMonitorListDto> getList(SearchParam searchParam) {
        return this.getSqlSession().selectList(namespace + ".listMonitor", searchParam);
    }

    public void updateDeadTime() {
        this.getSqlSession().update(namespace + ".updateDeadTime");
    }

    public List<PatentCostMonitorDto> findEmails(SearchParam searchParam) {
        return this.getSqlSession().selectList(namespace + ".findEmails", searchParam);
    }

    public Collection<? extends String> getListCostId(SearchParam searchParam) {
        return this.getSqlSession().selectList(namespace + ".listMonitorCostId", searchParam);
    }
}
