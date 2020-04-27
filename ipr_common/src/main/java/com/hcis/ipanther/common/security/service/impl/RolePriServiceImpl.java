package com.hcis.ipanther.common.security.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.security.dao.PrivilegeDao;
import com.hcis.ipanther.common.security.dao.RolePriDao;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.entity.RolePri;
import com.hcis.ipanther.common.security.service.IRolePriService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;
@Service("rolePriService")
public class RolePriServiceImpl extends BaseServiceImpl<RolePri> implements IRolePriService {
	@Resource
	private PrivilegeDao privilegeDao;
	@Resource
	private RolePriDao rolePriDao;
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#list(SearchParam searchParam)
	 */
	@Override
	public List<RolePri> list(SearchParam searchParam) {
		return rolePriDao.selectBySearchParam(searchParam);
	}

//	/* (non-Javadoc)
//	 * @see com.hcis.ipanther.core.service.IBaseService#get(java.lang.Object)
//	 */
//	@Override
//	public RolePri get(RolePri rolePri) {
//		return rolePriDao.selectByPrimaryKey(rolePri);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hcis.ipanther.core.service.IBaseService#save(java.lang.Object)
//	 */
//	@Override
//	public int save(RolePri rolePri) {
//		
//		return 0;
//	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#delete(java.lang.Object)
	 */
	@Override
	@Caching(
			evict={
					@CacheEvict(value = "iprcache:privilege", key = "'iprcache:privilege:all'"),
			}
	)
	public int delete(RolePri rolePri) {
		return rolePriDao.deleteByPhysics(rolePri);
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#batchDelete(java.util.List,java.lang.String)
	 */
	@Override
	@Caching(
			evict={
					@CacheEvict(value = "iprcache:privilege", key = "'iprcache:privilege:all'"),
			}
	)
	public int batchDelete(List<RolePri> ids,String updatedby) {
		return rolePriDao.batchDeleteByPhysics(ids);
	}
	
	@Override
	@CacheEvict(value = "iprcache:privilege", key = "'iprcache:privilege:all'")
	public int updateRolePri(SearchParam searchParam) {
		Object priIdsObj=searchParam.getParamMap().get("privilegeId");
		String roleId=searchParam.getParamMap().get("roleId").toString();
		String priIds=priIdsObj==null?"":priIdsObj.toString();
		//删除原有的功能对应关系
		int delNum=rolePriDao.deleteByModelIdAndRoleId(searchParam);
		int insertNum=0;
		//循环插入新的功能对应关系
		if(priIds!=null&&!priIds.equals("")){
			String[] ids=priIds.split(";");
			insertNum=ids.length;
			RolePri rp=new RolePri();
			rp.setRoleId(roleId);
			for(String id:ids){
				rp.setPrivilegeId(id);
				rolePriDao.insert(rp);
			}
		}
		return delNum+insertNum;
	}
	@Override
	public List<Map<String, Object>> rolePriTree(Role role,Module module) {
/*		SearchParam searchParam=new SearchParam();
		Pagination p=new Pagination();
		p.setAvailable(false);
		searchParam.setPagination(p);*/
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("module",role.getModule());
		map.put("roleId", role.getId());
		List<Map<String, Object>> listRolePri= privilegeDao.selectListRolePri(map);
		if(CollectionUtils.isEmpty(listRolePri)){
			List<Map<String, Object>> listPrivilege	= privilegeDao.selectListPrivilege(map);
			for (Map<String, Object> privilege : listPrivilege) {
				if(privilege!=null&&(!privilege.isEmpty())){
					privilege.put("checked", false);
				}
			}
			return listPrivilege;
		}else{
			for (Map<String, Object> rolePri : listRolePri) {
				if(rolePri!=null&&(!rolePri.isEmpty())){
					String checked= ObjectUtils.toString(rolePri.get("checked"));
					if(checked!=null&&Integer.parseInt(checked)==1){
						rolePri.put("checked", true);
					}else{
						rolePri.put("checked", false);
					}
				}
			}
			return listRolePri;
		}
		//List<Privilege> privilegeList=privilegeDao.selectBySearchParam(searchParam);
	
		//List<RolePri> rpList=rolePriDao.selectBySearchParam(searchParam);
		//return this.createRolePriTree(privilegeList, rpList,module);
	}
	
	/**
	 * 生成树（用于角色配置功能）
	 * @param list
	 * @param rolePri
	 * @return
	 */
	public String createRolePriTree(List<Privilege> list,List<RolePri> rpList,Module module){
		String rootName="root";
		if(StringUtils.isNotBlank(module.getName())){
			rootName=module.getName();
		}
		//生成[{ id:0, pId:-1, name:"root", open:true， checked:true/false}]格式
		StringBuffer treeStr=new StringBuffer();
		treeStr.append("[{ id:'0', pId:'-1', name:'"+rootName+"', open:true, 'nocheck':true}");
		String checked;
		if(rpList!=null&&rpList.size()>0){
			for(Privilege p:list){
				checked=",checked:false";
				for(RolePri rp:rpList){
					if(rp.getPrivilegeId().equals(p.getId())){
						checked=",checked:true";
					}
				}
				treeStr.append(",{id:'"+p.getId()+"',pId:'"+p.getPid()+"', name:'"+p.getName()+"', open:true"+checked+"}");
			}
		}else{
			for(Privilege p:list){
				treeStr.append(",{id:'"+p.getId()+"',pId:'"+p.getPid()+"', name:'"+p.getName()+"', open:true}");
			}
		}
		
		treeStr.append("]");
		return treeStr.toString();
	}

	@Override
	public MyBatisDao getBaseDao() {
		return null;
	}

	//递归查询
	public Object selectByPriId(Map map) {
		
		return rolePriDao.selectByPriId(map);
	}


}
