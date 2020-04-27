package com.hcis.ipanther.common.security.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.dao.PrivilegeDao;
import com.hcis.ipanther.common.security.dao.RolePriDao;
import com.hcis.ipanther.common.security.entity.Module;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.service.IPrivilegeService;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements IPrivilegeService {
	@Resource
	private PrivilegeDao privilegeDao;
	@Resource
	private RolePriDao rolePriDao;
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#list(SearchParam searchParam)
	 */
	@Override
	public List<Privilege> list(SearchParam searchParam) {
		return privilegeDao.selectBySearchParam(searchParam);
	}
	
	@Override
	@Cacheable(value = "iprcache:privilege", key = "'iprcache:privilege:all'")
	public List<Privilege> listAllPrivilegeRole(){
		return privilegeDao.selectAllPrivilegeRole();
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#get(java.lang.Object)
	 */
//	@Override
	public Privilege read(String privilegeId) {		
		return privilegeDao.selectByPrimaryKey(privilegeId);
	}

	/**
	 * 删除功能的同时，与角色的对应关系也得删除掉
	 */
	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#delete(java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "iprcache:privilege", key = "'iprcache:privilege:all'")
	public int delete(Privilege privilege,String userId) {
		if(privilege!=null){
			privilege.setUpdateTime(new Date());
			if(userId!=null){
				privilege.setUpdatedby(userId);
			}

			/*Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", privilege.getId());
			rolePriDao.selectByPriId(map);
			// 从数据库递归查询返回的结果集
			String result = map.get("result").toString();
			String priId = result.substring(2, result.length());
			rolePriDao.deleteByPriId(priId);
			return privilegeDao.deleteByPhysics(priId);
			//return privilegeDao.deleteByPhysics(privilege);*/

			List<String> privilegeIds = new ArrayList<>();
			privilegeIds.add(privilege.getId());
			getChild(privilegeIds, privilege.getId());

			rolePriDao.deleteBatch(privilegeIds);
			return privilegeDao.deleteBatch(privilegeIds);
		}
		return 0;
	}

	private void getChild(List<String> list, String privilegeId){
		List<String> child = privilegeDao.getChild(privilegeId);
		if(!CollectionUtils.isEmpty(child)){
			list.addAll(child);
			for(String pid : child){
				getChild(list, pid);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hcis.ipanther.core.service.IBaseService#batchDelete(java.util.List,java.lang.String)
	 */
	@Override
	@CacheEvict(value = "iprcache:privilege", key = "'iprcache:privilege:all'")
	public int batchDelete(List<Privilege> ids,String updatedby) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("list", ids);
		param.put("updatedby", updatedby);
		param.put("updateTime", new Date());
		return privilegeDao.batchDeleteByPhysics(param);
	}

    @Override
    public List<Privilege> getUserPrivileges(String moduleId) {
		if(SecurityUtils.getSubject().isAuthenticated()){
			LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
			/*if(moduleId!=null){
				if(loginUser.getMenuMap().containsKey(moduleId)){
					return loginUser.getMenuMap().get(moduleId);
				}
			}
			else{
				return loginUser.getMenu();
			}*/
			return loginUser.getPrivileges();
		}

		return null;
    }

    /**
	 * 返回生成树（用于功能的新增改查）
	 * @param module
	 * @return
	 */
	@Override
	public List<Map<String, Object>> treeView(SearchParam searchParam,Module module) {
		Map<String, Object> map=new HashMap<>();
		map.put("module",module.getId());
		List<Map<String, Object>> list=privilegeDao.selectListPrivilege(map);
		return list;
	//	return this.createTree(list,module);
	}

	/**
	 * 生成树（用于功能的新增改查）
	 * @param list
	 * @return
	 */
	public String createTree(List<Privilege> list,Module module){
		String rootName ="root";
		if(StringUtils.isNotBlank(module.getName())){
			rootName=module.getName();
		}
		//生成[{ id:0, pId:-1, name:"root", open:true}]格式
		StringBuffer treeStr=new StringBuffer();
		treeStr.append("[{ id:'0', pId:'-1', name:'"+rootName+"', open:true}");
		for(Privilege p:list){
			treeStr.append(",{id:'"+p.getId()+"',pId:'"+p.getPid()+"', name:'"+p.getName()+"', open:true}");
		}
		treeStr.append("]");
		return treeStr.toString();
	}

	@Override
	@CacheEvict(value = "iprcache:privilege", key = "'iprcache:privilege:all'")
	public int save(Privilege privilege) {
//		LoginUser loginUser=LoginUtils.getLoginUser(request);
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		if(privilege!=null){
			if(StringUtils.isNotEmpty((privilege.getId()))){
				privilege.setUpdateTime(new Date());
				if(loginUser!=null){
					privilege.setUpdatedby(loginUser.getId());
				}
				return privilegeDao.updateByPrimaryKeySelective(privilege);
			}else{
				String uuid = Identities.uuid2();
				privilege.setId(uuid);
				privilege.setDefaultValue();
				if(loginUser!=null){
					privilege.setCreator(loginUser.getId());
				}
				return privilegeDao.insertSelective(privilege);
			}
		}
		return 0;
	}

	@Override
	public boolean checkPrivilegeName(String privilegeName, String oldPrivilegeName) {
		int count=0;
		SearchParam s=new SearchParam();
		Map<String,Object> m=s.getParamMap();
		m.put("privilegeName", privilegeName);
		m.put("oldPrivilegeName", oldPrivilegeName);
		String countStr =privilegeDao.selectCountByName(s);
		if(null!=countStr&&!countStr.equals("")){
			count=Integer.valueOf(countStr);
		}
		return count>0?false:true;
	}

	@Override
	public PrivilegeDao getBaseDao() {
		return privilegeDao;
	}
}
