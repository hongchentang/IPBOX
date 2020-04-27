package com.hcis.ipr.train.register.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.excel.config.ExcelConfig;
import com.hcis.ipanther.common.excel.convert.ExcelTOModelService;
import com.hcis.ipanther.common.excel.model.ImportFailModel;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.ExportUtils;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.register.dao.CourseRegisterDao;
import com.hcis.ipr.train.register.entity.CourseRegister;
import com.hcis.ipr.train.register.model.CourseRegisterModel;
import com.hcis.ipr.train.register.service.ICourseRegisterService;
import com.hcis.ipr.train.register.utils.CourseRegisterUtils;
import com.hcis.ipr.train.train.service.ITrainService;

@Service("courseRegisterService")
public class CourseRegisterServiceImpl extends BaseServiceImpl<CourseRegister> implements ICourseRegisterService{

	private final static Log log = LogFactory.getLog(CourseRegisterServiceImpl.class);
	@Autowired
	private CourseRegisterDao courseRegisterDao;
	@Autowired
	private ITrainService trainService;
	@Autowired
	private ExcelTOModelService excelTOModelService;
	
	@Override
	public MyBatisDao getBaseDao() {
		return courseRegisterDao;
	}

	//查看学员成绩列表
	public List<Map<String,Object>> listScoreInfo(SearchParam searchParam){
		return courseRegisterDao.listScoreInfo(searchParam);
	}

	//根据班导出模板
	public void exportTemplate(SearchParam searchParam,HttpServletResponse response){
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String,Object> paramMap=searchParam.getParamMap();
		Pagination p=new Pagination();
		p.setAvailable(false);
		searchParam.setPagination(p);
		String trainName=paramMap.get("trainName")==null?"":paramMap.get("trainName").toString();
		List<Map<String, Object>> userInfoList=courseRegisterDao.selectScoreInfo(searchParam);
		Map<String,Object> beans = new HashMap<String,Object>();
		beans.put("yonghu", userInfoList);
		beans.put("dateUtils", new CourseRegisterUtils());
		try{
			ExportUtils.exportExcel(response, "/excel/courseRegister.xls", beans, trainName+"班学员课程导出"+DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
		}catch(Exception e){
			log.error("导出出错",e);
		}
	/*	String[] titles = {"编码","学员姓名","部门","课程名称","分数","结果"};
		List<String[]> dataList = new ArrayList<String[]>();
		if(null!=userInfoList&& userInfoList.size()>0){
			for(Map<String,Object> m:userInfoList){
				String[] data = new String[6];
				data[0]=m.get("id").toString();
				data[1]=m.get("realName")==null?"":m.get("realName").toString();
				data[2]=m.get("deptName")==null?"":m.get("deptName").toString();
				data[3]=m.get("courseName")==null?"":m.get("courseName").toString();
				data[4]=m.get("score")==null?"":m.get("score").toString();
				data[5]=m.get("result")==null?"无":m.get("result").toString();
				if(null!=m.get("result")){
					if(m.get("result").toString().equals("1")){
						data[5]="通过";
					}else if(m.get("result").toString().equals("0")){
						data[5]="未通过";
					}
				}
				dataList.add(data);
			}
		}else {
			dataList.add(new String[]{"无数据！"});
		}
		try {
			ExcelExport.outSuccessExcel(dataList, trainName+"班学员课程导出"+DateFormatUtils.format(new Date(), "yyyy-MM-dd"), titles, response, null);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	//保存成绩导入
	public AjaxReturnObject saveImport(MultipartFile file,HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		LoginUser loginUser=LoginUser.loginUser(request);
		List<ImportFailModel> failList = new ArrayList<ImportFailModel>();//校验失败的
		List<CourseRegisterModel> successList = new ArrayList<CourseRegisterModel>();
		HSSFWorkbook book=null;
		CourseRegister cr=null;
		CourseRegisterModel model=null;
		String batchId = Identities.uuid2();//生成批次ID
		int statusCode=200;
		String resultMsg=batchId;
		String fileType=StringUtils.substringAfterLast(file.getOriginalFilename().toLowerCase(), ".");
		//判断上传文件格式
		if(!fileType.equals("xls")){
			return new AjaxReturnObject(300,"上传文件格式有误！");
		}
		try{
			InputStream inputStream = file.getInputStream();
			//FileInputStream in = (FileInputStream)inputStream;
			//得到工作表
			book = new HSSFWorkbook(inputStream);
			List<Object> list = excelTOModelService.getModelList(book,"CourseRegisterModel", failList);
			HSSFSheet sheet = book.getSheetAt(0);
			
			for(Object obj:list){
				cr=new CourseRegister();
				model=(CourseRegisterModel) obj;
				cr.setId(model.getCourseRegisterId());
				BigDecimal score=null;
				if(StringUtils.isNotBlank(model.getScore())){
					score=new BigDecimal(model.getScore());
				}
				cr.setScore(score);
				String result=model.getResult();
				if(null!=result&&result.toString().equals("通过")){
					cr.setResult("1");
				}else{
					cr.setResult("0");
				}
				//更新分数
				int count=this.update(cr, loginUser.getId());
				if(count>0){
					HSSFRow row = sheet.getRow(model.getRownum());
					HSSFCell resultCell = row.createCell((short)(row.getPhysicalNumberOfCells()));
					resultCell.setCellValue("数据更新成功！");
					successList.add(model);
				}else{
					HSSFRow row = sheet.getRow(model.getRownum());
					HSSFCell resultCell = row.createCell((short)(row.getPhysicalNumberOfCells()));
					resultCell.setCellValue("编码有误！数据更新失败！");
					ImportFailModel failModel=new ImportFailModel(model.getRownum(),"编码有误！更新失败！");
					failList.add(failModel);
				}
			}
			String id = Identities.uuid2();
			String path = request.getSession().getServletContext().getRealPath("/");
			
			String fileName = path+ExcelConfig.getProperty("excel.file.path")+id+".xls";
			File outputFolder=new File(path+ExcelConfig.getProperty("excel.file.path"));
			if(!outputFolder.exists()){
				outputFolder.mkdirs();
			}
			File outputFile=new File(fileName);
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			resultMap.put("successList", successList);
			resultMap.put("failList", failList);
			resultMap.put("filePath", ExcelConfig.getProperty("excel.file.path")+id+".xls");
			resultMap.put("message", "成功导入"+successList.size()+"条数据！");//导出结果的整体信息
			OutputStream output = new FileOutputStream(outputFile);
			book.write(output);
			output.close();
			request.getSession().setAttribute(batchId, resultMap);
			
		}catch(Exception e){
			 statusCode=300;
			 resultMsg="导入模板不正确，请检查模板是否有误！";
			 e.printStackTrace();
		}
		
		return new AjaxReturnObject(statusCode,resultMsg);
	}
	
	
	//批量删除
	@Override
	public int batchUpdate(SearchParam searchParam) {
		return courseRegisterDao.batchUpdate(searchParam);
	}

}
