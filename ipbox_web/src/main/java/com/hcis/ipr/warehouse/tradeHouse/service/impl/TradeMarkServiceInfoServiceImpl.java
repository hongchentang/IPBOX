package com.hcis.ipr.warehouse.tradeHouse.service.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.warehouse.tradeHouse.dao.TradeMarkServiceInfoDao;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkServiceInfo;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkServiceInfoService;

@Service("tradeMarkServiceInfoServiceImpl")
public class TradeMarkServiceInfoServiceImpl extends BaseServiceImpl< TradeMarkServiceInfo> implements ITradeMarkServiceInfoService {

	private final static Log log=LogFactory.getLog(TradeMarkServiceInfoServiceImpl.class);
	@Autowired
	private TradeMarkServiceInfoDao tradeMarkServiceInfoDao;

	@Override
	public List<TradeMarkServiceInfo> list(SearchParam searchParam) {
		return tradeMarkServiceInfoDao.selectBySearchParam(searchParam);
	}
	@Override
	public TradeMarkServiceInfoDao getBaseDao() {
		return tradeMarkServiceInfoDao;
	}
	
	
	@Override
	public String importTradeMarkServiceInfo(String type,MultipartFile file, HttpServletRequest request){
		//生成批次ID
		String batchId = Identities.uuid2();
		return batchId;
	}

}
