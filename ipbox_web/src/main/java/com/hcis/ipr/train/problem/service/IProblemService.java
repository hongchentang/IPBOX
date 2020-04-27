package com.hcis.ipr.train.problem.service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.problem.entity.Problem;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract interface IProblemService
{
  public abstract List<Map<String, Object>> list(SearchParam paramSearchParam);

  public abstract int delete(Problem paramProblem);

  public abstract Problem getByPk(Problem paramProblem);

  public abstract boolean checkSameCode(Problem paramProblem);

  public abstract int addOrUpdate(Problem paramProblem, LoginUser loginUser);

  public abstract int delete(String[] paramArrayOfString);

  public abstract Problem queryProblemById(String paramString);
}