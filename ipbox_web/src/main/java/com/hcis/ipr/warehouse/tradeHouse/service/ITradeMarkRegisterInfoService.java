package com.hcis.ipr.warehouse.tradeHouse.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkRegisterInfo;

public interface ITradeMarkRegisterInfoService extends IBaseService<TradeMarkRegisterInfo>{

	String importTradeMarkRegister(String type,MultipartFile file, HttpServletRequest request);
	
}
