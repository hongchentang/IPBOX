package com.hcis.ipr.train.problem.service.impl;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.problem.dao.ProblemDao;
import com.hcis.ipr.train.problem.entity.Problem;
import com.hcis.ipr.train.problem.service.IProblemService;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("problemService")
public class ProblemServiceImpl
  implements IProblemService
{

  @Autowired
  private ProblemDao problemDao;

  public List<Map<String, Object>> list(SearchParam searchParam)
  {
    return this.problemDao.selectBySearchParam(searchParam);
  }

  public int delete(Problem t)
  {
    return this.problemDao.deleteByPrimaryKey(t.getId());
  }

  public Problem getByPk(Problem t)
  {
    return (Problem)this.problemDao.selectByPrimaryKey(t);
  }

  public boolean checkSameCode(Problem problem)
  {
    problem = (Problem)this.problemDao.selectByPrimaryKey(problem);
    if (problem != null) {
      return false;
    }
    return true;
  }

  public int addOrUpdate(Problem t, LoginUser loginUser)
  {
    if (t.getVersion() == 0) {
      if (loginUser != null) {
        t.setCreator(loginUser.getId());
      }
      t.setDefaultValue();
      t.setId(Identities.uuid2());
      return this.problemDao.insertSelective(t);
    }
    if (loginUser != null) {
      t.setUpdatedby(loginUser.getId());
    }
    t.setUpdateTime(new Date());
    return this.problemDao.updateByPrimaryKeySelective(t);
  }

  public int delete(String[] idArray)
  {
    int count = this.problemDao.deleteByIdArray(idArray);
    return count;
  }

  public Problem queryProblemById(String code) {
    return (Problem)this.problemDao.selectByPrimaryKey(code);
  }
}