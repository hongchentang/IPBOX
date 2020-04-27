package com.hcis.ipr.warehouse.tradeHouse.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.DateUtils;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.warehouse.tradeHouse.dao.TradeMarkHouseDao;
import com.hcis.ipr.warehouse.tradeHouse.dao.TradeMarkRegisterInfoDao;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkHouse;
import com.hcis.ipr.warehouse.tradeHouse.entity.TradeMarkRegisterInfo;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkRegisterInfoService;
import com.hcis.ipr.warehouse.tradeHouse.service.ITradeMarkHouseService;



@Service("tradeMarkRegisterInfoServiceImpl")
public class TradeMarkRegisterInfoServiceImpl extends BaseServiceImpl<TradeMarkRegisterInfo> implements ITradeMarkRegisterInfoService {

	private final static Log log=LogFactory.getLog(TradeMarkRegisterInfoServiceImpl.class);
	@Autowired
	private TradeMarkRegisterInfoDao tradeMarkRegisterInfoDao;


	@Override
	public List<TradeMarkRegisterInfo> list(SearchParam searchParam) {
		return tradeMarkRegisterInfoDao.selectBySearchParam(searchParam);
	}
	@Override
	public TradeMarkRegisterInfoDao getBaseDao() {
		return tradeMarkRegisterInfoDao;
	}
	
	
	@Override
	public String importTradeMarkRegister(String type,MultipartFile file, HttpServletRequest request){
		//生成批次ID
		String batchId = Identities.uuid2();
		try {
			LoginUser loginUser = LoginUser.loginUser(request);
			String loginUserId = loginUser.getId();
			
			InputStream inputStream = file.getInputStream();		
//			FileInputStream in = (FileInputStream)inputStream;
			//得到工作表
//			HSSFWorkbook book = new HSSFWorkbook(inputStream);
			Workbook book = WorkbookFactory.create(inputStream);
		}catch(Exception e)
		{
			log.error(this.getClass(),e);
			e.printStackTrace();
			return "";
		}
		return batchId;
	}
	
}
