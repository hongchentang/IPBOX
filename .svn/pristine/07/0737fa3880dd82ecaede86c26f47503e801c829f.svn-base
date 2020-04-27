package com.hcis.ipr.intellectual.cost.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.cost.dto.PatentCostDetailDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostListDto;
import com.hcis.ipr.intellectual.cost.entity.WarnTimeLine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhw
 */
@Repository("warnTimeLineDao")
public class WarnTimeLineDao extends MyBatisDao {
    public WarnTimeLine getByCompanyId(String companyId) {
        return this.getSqlSession().selectOne(namespace + ".getByCompanyId", companyId);
    }

    public void updateTimeLine(WarnTimeLine warnTimeLine) {
        this.getSqlSession().update(namespace + ".updateTimeLine", warnTimeLine);
    }
}
