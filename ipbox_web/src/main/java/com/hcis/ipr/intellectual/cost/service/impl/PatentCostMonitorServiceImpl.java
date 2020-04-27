package com.hcis.ipr.intellectual.cost.service.impl;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.cost.dao.PatentCostDao;
import com.hcis.ipr.intellectual.cost.dao.PatentCostMonitorDao;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorDto;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorListDto;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.entity.PatentCostMonitor;
import com.hcis.ipr.intellectual.cost.entity.WarnTimeLine;
import com.hcis.ipr.intellectual.cost.enumeration.CostType;
import com.hcis.ipr.intellectual.cost.service.PatentCostMonitorService;
import com.hcis.ipr.intellectual.cost.service.WarnTimeLineService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhw
 * @date 2019/8/29
 **/
@Service
public class PatentCostMonitorServiceImpl extends BaseServiceImpl<PatentCostMonitor> implements PatentCostMonitorService {

    @Autowired
    private PatentCostMonitorDao patentCostMonitorDao;

    @Autowired
    private PatentCostDao patentCostDao;

    @Autowired
    private WarnTimeLineService warnTimeLineService;

    @Autowired
    private IUserService userService;

    @Override
    public MyBatisDao getBaseDao() {
        return patentCostMonitorDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void monitorRefresh(String companyId) {

        Date date = new Date();
        Date nextDate = DateUtils.addYears(date, 1);
        Date lastDate = DateUtils.addYears(date, -1);

        Map<String, Object> query = new HashMap<>(3);
        query.put("companyId", companyId);
        query.put("maxDate", nextDate);
        query.put("minDate", lastDate);

        //查询出需要添加到监控列表的数据
        List<PatentCost> patentCosts = patentCostDao.selectListNeedMonitor(query);

        //整理下
        List<PatentCostMonitor> addList = new ArrayList<>();
        for(PatentCost patentCost : patentCosts){
            PatentCostMonitor monitor = new PatentCostMonitor();
            monitor.setId(UUIDUtils.getUUId());
            monitor.setStatus(false);
            monitor.setPatentCostId(patentCost.getId());
            monitor.setCreator(patentCost.getCreator());
            monitor.setUpdatedby(patentCost.getCreator());
            monitor.setCompanyId(patentCost.getCompanyId());
            monitor.setDefaultValue();

            addList.add(monitor);
        }

        //批量插入
        if(addList.size() > 0){
            patentCostMonitorDao.batchInsert(addList);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PatentCostMonitorListDto> getList(SearchParam searchParam) {

        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String companyId = user.getCompanyId();
        if(ObjectUtils.notEqual(UserConstants.USER_ROLECODE_SUPER_ADMIN,user.getRoleCode())){
            searchParam.getParamMap().put("companyId", companyId);
        }
        int costType = Integer.parseInt(searchParam.getParamMap().get("type").toString());
        WarnTimeLine warnTimeLine = warnTimeLineService.getByCompanyId(companyId);
        Integer maxTime = null;
        if(costType == CostType.ANNUAL_FEE.getType()){
            maxTime = warnTimeLine.getOneTimeLine();
        }else if(costType == CostType.GOVERNMENT_FEE.getType()){
            maxTime = warnTimeLine.getTwoTimeLine();
        }
        searchParam.getParamMap().put("maxTime", maxTime);

        //xx
        int type = Integer.parseInt((String) searchParam.getParamMap().get("type"));
        if(CostType.ANNUAL_FEE.getType() == type){
            searchParam.getParamMap().put("monitorMaxDay", -180);
        }else if(CostType.GOVERNMENT_FEE.getType() == type){
            searchParam.getParamMap().put("monitorMaxDay", -30);
        }

        //权限相关数据限制
        searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
        searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());
        return patentCostMonitorDao.getList(searchParam);
    }

    @Override
    public List<PatentCostMonitorDto> findEmails(Integer max, Integer min,Integer costType) {
        SearchParam searchParam = new SearchParam();
        searchParam.getParamMap().put("max", max);
        searchParam.getParamMap().put("min", min);
        Pagination pagination = new Pagination();
        pagination.setPageSize(3);
        searchParam.setPagination(pagination);
        searchParam.getParamMap().put("costType", costType);
        searchParam.getParamMap().put("year",  Calendar.getInstance().get(Calendar.YEAR));
        return patentCostMonitorDao.findEmails(searchParam);
    }

    /**
     *                 s  m  h d M y
     */
    @Scheduled(cron = "0 55 10 * * ?")
    @Transactional(rollbackFor = Exception.class )
    protected void testQuartz(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始定时任务!!" + format.format(date));
        //更新距离期限日期(全表)
        patentCostMonitorDao.updateDeadTime();
        //更新监控数据(所有数据)
        monitorRefresh("");
        //更新已过期数据

        System.out.println("结束定时任务!!" + format.format(new Date()));
    }
}
