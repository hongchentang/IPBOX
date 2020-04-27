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
import com.hcis.ipr.warehouse.tradeHouse.dao.TradeMarkHouseDao;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkHouse;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkHouseService;



@Service("tradeMarkService")
public class TradeMarkHouseServiceImpl extends BaseServiceImpl<TradeMarkHouse> implements ITradeMarkHouseService {

	private final static Log log=LogFactory.getLog(TradeMarkHouseServiceImpl.class);
	@Autowired
	private TradeMarkHouseDao tradeMarkHouseDao;

	@Override
	public List<TradeMarkHouse> list(SearchParam searchParam) {
		return tradeMarkHouseDao.selectBySearchParam(searchParam);
	}
	@Override
	public TradeMarkHouseDao getBaseDao() {
		return tradeMarkHouseDao;
	}
	
	@Override
	public String importTradeMark(String type,MultipartFile file, HttpServletRequest request){
		//生成批次ID
		String batchId = Identities.uuid2();
		return batchId;
	}
}
