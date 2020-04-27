package com.hcis.ipr.cms.config.service.impl;


import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipr.monogo.utils.JsonStrToMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.cms.config.dao.CmsConfigDao;
import com.hcis.ipr.cms.config.entity.CmsConfig;
import com.hcis.ipr.cms.config.service.ICmsConfigService;

import java.util.Date;
import java.util.Map;

@Service("cmsConfigService")
public class CmsConfigServiceImpl extends BaseServiceImpl<CmsConfig> implements ICmsConfigService {

	
	@Autowired
	private CmsConfigDao  baseDao;
	
	
	
	
	
	@Override
	public CmsConfigDao getBaseDao() {
		return baseDao;
	}

    @Override
    public void saveOauth2Config(String reJsonStr, String openId, String openKey) {
        if(StringUtils.isNotBlank(reJsonStr)){

			Map<String, Object> map = JsonStrToMap.jsonStrToMap(reJsonStr);
			Integer code = (Integer) map.get("code");
			if(code.equals(0)){
				//保存配置
				map.put("openId", openId);
				map.put("openKey", openKey);

				CmsConfig cmsConfig = null;
				cmsConfig = this.baseDao.selectByPrimaryKey("OAUTH2_ACCESS_TOKEN");
				if(cmsConfig == null){
					cmsConfig = new CmsConfig();
					cmsConfig.setCode("OAUTH2_ACCESS_TOKEN");
					cmsConfig.setConfigValue(JSONUtils.getJSONString(map));
					cmsConfig.setDefaultValue();
					this.baseDao.insert(cmsConfig);
				}else {
					cmsConfig.setConfigValue(JSONUtils.getJSONString(map));
					cmsConfig.setUpdateTime(new Date());
					this.baseDao.updateByPrimaryKeySelective(cmsConfig);
				}

			}
		}
    }
}
