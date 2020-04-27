/**
 * 
 */
package com.hcis.ipanther.core.service;

import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chao
 *  2013年12月6日 下午4:29:30
 *
 * 实现具体增删改查的Service接口
 */
public interface IBaseService<T extends BaseEntity> {
    
    public List<T> list(SearchParam searchParam);
    
    public T read(String id);
    
    public int create(T obj,String creator);
    
    public int update(T obj,String updateBy);
    
    public int delete(T obj,String updateBy);
    
}
