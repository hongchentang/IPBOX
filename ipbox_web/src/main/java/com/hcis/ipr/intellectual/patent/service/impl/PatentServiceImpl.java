package com.hcis.ipr.intellectual.patent.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hcis.datas.DataSource;
import com.hcis.datas.DbContextHolder;
import com.hcis.ipanther.common.dict.utils.DictUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.dao.UserDeptDao;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.common.utils.ReadExcel;
import com.hcis.ipr.intellectual.patent.dao.PatentAttachmentDao;
import com.hcis.ipr.intellectual.patent.dao.PatentDao;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.intellectual.patent.entity.PatentAttachment;
import com.hcis.ipr.intellectual.patent.enumeration.AttachmentEnum;
import com.hcis.ipr.intellectual.patent.service.PatentAttachmentService;
import com.hcis.ipr.intellectual.patent.service.PatentService;
import com.hcis.ipr.warehouse.patentHouse.dao.PatentMongoDao;
import com.hcis.ipr.warehouse.patentHouse.service.IPatentMongoService;
import com.hcis.items.service.ItemsLibraryService;

import net.sf.json.JSONObject;

@Service("patentService")
public class PatentServiceImpl extends BaseServiceImpl<Patent> implements PatentService {

	private static final Integer INVALID = 3;

	@Autowired
	private PatentDao patentDao;

	@Autowired
	private IPatentMongoService patentMongoService;

	@Autowired
	private PatentAttachmentDao patentAttachmentDao;

	@Autowired
	private ItemsLibraryService itemsLibraryService;

	@Autowired
	private PatentAttachmentService patentAttachmentService;

	@Autowired
	private IUserService userService;

	@Autowired
	private UserDeptDao userDeptDao;

	@Autowired
	private PatentMongoDao patentMongoDao;

	@Autowired
	private IDepartmentService departmentService;

	@Override
	public void deleteByIds(List<String> patentIds) {
		patentDao.deleteByPrimaryKeys(patentIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addPatent(Patent patent, MultiValueMap<String, MultipartFile> fileMap) {
		String creator = patent.getCreator();
		String appNumber = patent.getAppNumber();

		// 保存专利信息

		setMongoPatentId(patent);
		String patentId = UUIDUtils.getUUId();
		patent.setId(patentId);
		patent.setDefaultValue();
		patentDao.insert(patent);

		// 保存附件信息
		List<PatentAttachment> attachments = new ArrayList<>();

		// 申请原文件
		List<MultipartFile> sourceFiles = fileMap.get(AttachmentEnum.SOURCE_FILE.getTypeName());
		uploadFileData(sourceFiles, attachments, creator, appNumber, patentId, AttachmentEnum.SOURCE_FILE.getType());

		// 受理通知书
		List<MultipartFile> authorizeFiles = fileMap.get(AttachmentEnum.AUTHORIZATION_FILE.getTypeName());
		uploadFileData(authorizeFiles, attachments, creator, appNumber, patentId, AttachmentEnum.AUTHORIZATION_FILE.getType());

		// 专利证书
		List<MultipartFile> patentFiles = fileMap.get(AttachmentEnum.PATENT_FILE.getTypeName());
		uploadFileData(patentFiles, attachments, creator, appNumber, patentId, AttachmentEnum.PATENT_FILE.getType());

		// 检索报告
		List<MultipartFile> searchFiles = fileMap.get(AttachmentEnum.SEARCH_FILE.getTypeName());
		uploadFileData(searchFiles, attachments, creator, appNumber, patentId, AttachmentEnum.SEARCH_FILE.getType());

		// 代理合同
		List<MultipartFile> agencyFiles = fileMap.get(AttachmentEnum.AGENCY_FILE.getTypeName());
		uploadFileData(agencyFiles, attachments, creator, appNumber, patentId, AttachmentEnum.AGENCY_FILE.getType());

		// 保存
		if (attachments.size() > 0) {
			patentAttachmentDao.insertBatch(attachments);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePatent(Patent patent, MultiValueMap<String, MultipartFile> fileMap) {

		String creator = patent.getUpdatedby();

		String appNumber = patent.getAppNumber();
		setMongoPatentId(patent);
		patent.setUpdateTime(new Date());
		patentDao.updateByPrimaryKey(patent);

		// 删除需要删除的文件信息
		String deleteAttachmentIdStr = patent.getDeleteAttachmentIds();
		if (StringUtils.isNotBlank(deleteAttachmentIdStr)) {
			String[] attachmentIds = deleteAttachmentIdStr.split(",");
			patentAttachmentDao.deleteByIds(attachmentIds);
		}

		// 保存附件信息
		List<PatentAttachment> attachments = new ArrayList<>();

		// 申请原文件
		List<MultipartFile> sourceFiles = fileMap.get(AttachmentEnum.SOURCE_FILE.getTypeName());
		uploadFileData(sourceFiles, attachments, creator, appNumber, patent.getId(), AttachmentEnum.SOURCE_FILE.getType());

		// 受理通知书
		List<MultipartFile> authorizeFiles = fileMap.get(AttachmentEnum.AUTHORIZATION_FILE.getTypeName());
		uploadFileData(authorizeFiles, attachments, creator, appNumber, patent.getId(), AttachmentEnum.AUTHORIZATION_FILE.getType());

		// 专利证书
		List<MultipartFile> patentFiles = fileMap.get(AttachmentEnum.PATENT_FILE.getTypeName());
		uploadFileData(patentFiles, attachments, creator, appNumber, patent.getId(), AttachmentEnum.PATENT_FILE.getType());

		// 检索报告
		List<MultipartFile> searchFiles = fileMap.get(AttachmentEnum.SEARCH_FILE.getTypeName());
		uploadFileData(searchFiles, attachments, creator, appNumber, patent.getId(), AttachmentEnum.SEARCH_FILE.getType());

		// 代理合同
		List<MultipartFile> agencyFiles = fileMap.get(AttachmentEnum.AGENCY_FILE.getTypeName());
		uploadFileData(agencyFiles, attachments, creator, appNumber, patent.getId(), AttachmentEnum.AGENCY_FILE.getType());

		// 保存
		if (attachments.size() > 0) {
			patentAttachmentDao.insertBatch(attachments);
		}

	}

	@Override
	public Patent getPatentInfo(String id) {
		// 基本信息
		Patent patent = patentDao.selectByPrimaryKey(id);

		// 附件信息
		patent.setAttachments(patentAttachmentService.selectPatentAttachmentsMap(patent.getAppNumber()));
		return patent;
	}

	@Override
	public List<Patent> patentList(SearchParam searchParam) {

		searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
		searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());

		return patentDao.selectBySearchParam(searchParam);
	}

	@Override
	public void updateBatch(List<Patent> updateList) {
		patentDao.updateBatch(updateList);
	}

	@Override
	public List<Patent> listConcatMongoPatent() {
		return patentDao.listConcatMongoPatent();
	}

	@Override
	public void refreshLegalStatus(List<String> ids) {

		List<Patent> patents = patentDao.selectByPrimaryKeys(ids);
		for (Patent patent : patents) {
			if (patent != null) {
				Document document = null;
				if (StringUtils.isNotBlank(patent.getMongoPatentId())) {
					document = patentMongoDao.getLegalById(patent.getMongoPatentId());
				}
				if (document == null) {
					document = patentMongoDao.getPatentByAppNumber(patent.getAppNumber());
					if (document != null) {
						String mongoPatentId = document.getString("_id");
						document = patentMongoDao.getLegalById(mongoPatentId);
						patent.setMongoPatentId(mongoPatentId);
					} else {
						continue;
					}
				}

				List documents = document.get("patent_legal_status", Document.class).get("legal_status_history",
						List.class);
				Object obj = documents.get(documents.size() - 1);
				String category = ((Document) obj).getString("category");
				Integer lastStatus = document.get("patent_legal_status", Document.class).getInteger("legal_status");

				// 转换法律状态并且修改专利信息
				convertLegalStatus(patent, category, lastStatus);

			}
		}
	}

	@Override
	public void costedPatent(Patent patent) {
		patentDao.costedPatent(patent);
	}

    @Override
    public List<String> getPatentFiles(String id) {
        return patentAttachmentDao.selectAllFile(id);
    }

	@DataSource(DataSource.DATA_SOURCE_SEC)
	@Override
	public Map test() {
		//DbContextHolder.setDataSource(DataSource.DATA_SOURCE_SEC);
		return patentDao.test();
	}

	@Override
	public List<Patent> selectByAppNuber(List<String> appNumber) {
		return patentDao.selectByAppNuber(appNumber);
	}

	@Override
	public List<Patent> selectByImportAppNuber(List<String> appNumberss) {
		List<Patent> listPatent=null;
		for (String str:appNumberss) {
			String appNumbers=str;
			String appNumber=null;
			int index=appNumbers.indexOf("CN");
			if(index==-1){
				appNumber=appNumbers;
			}else {
				appNumber=appNumbers.substring(2,appNumbers.length());
			}
			int zero=appNumber.indexOf(".");
			if (zero!=-1){
				String zerofirst=appNumber.substring(0,appNumber.length()-2);
				String zeroslast=appNumber.substring(appNumber.length()-1,appNumber.length());
				appNumber=zerofirst+zeroslast;
			}

			String tempfirsh=appNumber.substring(0,appNumber.length()-1);
			String templast=appNumber.substring(appNumber.length()-1,appNumber.length());
			String appNumberType=tempfirsh+"."+templast;
			String appNumberTypezero=tempfirsh+templast;
			String appNumberTypes="CN"+appNumberType;
			String appNumberTypeszero="CN"+appNumberTypezero;

			List<String> list=new ArrayList<String>();
			list.add(appNumber);

			list.add(appNumberType);
			list.add(appNumberTypes);
			list.add(appNumberTypeszero);
			 listPatent =selectByAppNuber(list);
		}



		return listPatent;
	}

	private void convertLegalStatus(Patent patent, String category, Integer lastStatus) {

		Integer resultCode = null;
		switch (category) {
		case "驳回":
			resultCode = 24;
			break;
		case "公开":
			resultCode = 7;
			break;
		case "实质审查":
			resultCode = 8;
			break;
		case "授权":
			resultCode = 29;
			break;
		default:
		}

		if (INVALID.equals(lastStatus)) {
			resultCode = 26;
		}

		String pLegalStatus = patent.getLegalStatus();
		if (resultCode != null) {
			String rCode = resultCode + "";
			if (!rCode.equals(pLegalStatus)) {
				Patent patentUpdate = new Patent();
				patentUpdate.setId(patent.getId());
				patentUpdate.setLegalStatus(rCode);
				patentUpdate.setMongoPatentId(patent.getMongoPatentId());
				patentDao.updateByPrimaryKeySelective(patentUpdate);
			}
		}
	}

	@Override
	public MyBatisDao getBaseDao() {
		return patentDao;
	}

	/**
	 * 专利的附件上传
	 * 
	 * @param files
	 * @param attachments
	 * @param creator
	 * @param attachmentType
	 */
	private boolean uploadFileData(List<MultipartFile> files, List<PatentAttachment> attachments, String creator,
			String appNumber, String patentId, int attachmentType) {
		boolean flag = false;
		for (MultipartFile file : files) {
			Map<String, String> map = new HashMap<>();
			String reStr = itemsLibraryService.saveFile(map, file, "attachment.default.fileTypes", "patent/file");
			if (StringUtils.isBlank(reStr) && map.size() > 0) {
				PatentAttachment attachment = new PatentAttachment();
				attachment.setDefaultValue();
				attachment.setCreator(creator);

				String attachmentFullName = map.get("attachmentName");
				String suffixName = attachmentFullName.substring(attachmentFullName.lastIndexOf("."));
				String attachmentName = attachmentFullName.replace(suffixName, "");

				attachment.setId(map.get("attachmentId"));
				attachment.setPatentId(patentId);
				attachment.setPatentAppNumber(appNumber);
				attachment.setFilePath(map.get("downloadUrl"));
				attachment.setFileName(attachmentName);
				attachment.setFileFullName(attachmentFullName);
				attachment.setFileSuffix(suffixName);
				attachment.setFileSize((double) file.getSize());
				attachment.setType(attachmentType);
				attachments.add(attachment);
				flag = true;
			}
		}

		return flag;
	}

	/* 添加专利后添加费用改变专利费用状态 */
	@Override
	public void updatePatentisCost(Patent patent) {
		// TODO Auto-generated method stub
		patent.setUpdateTime(new Date());
		patentDao.updateByPrimaryKey(patent);
	}

	private void setMongoPatentId(Patent patent) {

		if (StringUtils.isBlank(patent.getMongoPatentId())) {
			JSONObject jObj = patentMongoService.selectByPatentByAppNumber(patent.getAppNumber());
			if (jObj != null && jObj.size() > 0) {
				patent.setMongoPatentId(jObj.getString("_id"));
			}
		}
	}

@Override
	public String readExcelFile(MultipartFile file) {
		// TODO Auto-generated method stub

		String result = "";
	    String results = "(申请号重复的数据)：";
		// 创建处理EXCEL的类
		ReadExcel readExcel = new ReadExcel();

		// 解析excel，获取上传的事件单
		List<Map<String, Object>> userList = readExcel.getExcelInfo(file);

		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String userId = user.getId();
		String companyId = user.getCompanyId();

		for (Map<String, Object> users : userList) {
			List<String> list=new ArrayList<String>();
			list.add(users.get("sqh").toString());
			List<Patent> listPatent=selectByImportAppNuber(list);
			if(listPatent.size()<=0){

			Patent patent = new Patent();
			patent.setCreator(userId);
			patent.setCompanyId(companyId);
			patent.setPatentName(users.get("patentname").toString());
			patent.setAppNumber(users.get("sqh").toString());



			String appDate = users.get("sqr").toString().replace("年", "-").replace("月", "-").replace("日", "");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			Date appdate;
			try {
				appdate = formatter.parse(appDate);
				patent.setAppDate(appdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			patent.setApplyer(users.get("zlqr").toString());
			patent.setInventor(users.get("fmr").toString());
			patent.setFirstInventor(users.get("dyfmr").toString());
			String gj=users.get("sqgj").toString();
			String date= DictUtils.getEntryValueByName("IPBOX_PATENT_AUTHOR_COUNTRY",gj);

			patent.setAuthorCountry(date);
			patent.setDepId(user.getFirstDeptId());
			System.out.println("Did======" + user.getFirstDeptId());
			String type = users.get("zllx").toString();
			if (type.equals("发明")) {
				patent.setPatentType("1");
			} else if (type.equals("外观")) {

				patent.setPatentType("3");
			} else {
				patent.setPatentType("2");
			}
			String is = users.get("sqfw").toString();
			if (is.equals("是")) {
				patent.setIsAuthorize("0");
			} else {
				patent.setIsAuthorize("1");
			}
			String cstName=null;
			cstName=users.get("khmc").toString();
			System.out.println("Name="+cstName);
			if(cstName.equals("0")){

			}else{
				patent.setCustomerName(cstName);
			}

			String sqrq=null;
			if(is.equals("是")){
				sqrq = users.get("sqrq").toString();
			}else{
				sqrq="";
			}


			Date isauthdate;
			try {
				if (patent.getIsAuthorize().equals("0")) {

					isauthdate = formatter2.parse(sqrq);
					patent.setAuthorizeDate(isauthdate);
				}else{

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setMongoPatentId(patent);
			String patentId = UUIDUtils.getUUId();
			patent.setId(patentId);
			patent.setDefaultValue();

			int ret = patentDao.insert(patent);
			if (ret == 0) {
				result = "插入数据库失败";
			}
		}else{
				results+=users.get("sqh").toString()+"、";
			}
	}
		if (userList != null && !userList.isEmpty()) {
			result = "成功";
		} else {
			result = "上传失败";
		}
		if(results.length()>15){
			return results;
		}else{
			return result;
		}

	}






}
