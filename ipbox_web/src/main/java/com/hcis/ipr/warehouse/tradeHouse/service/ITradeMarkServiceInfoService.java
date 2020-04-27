package com.hcis.ipr.warehouse.tradeHouse.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkServiceInfo;

public interface ITradeMarkServiceInfoService extends IBaseService<TradeMarkServiceInfo>{

	String importTradeMarkServiceInfo(String type,MultipartFile file, HttpServletRequest request);
}
