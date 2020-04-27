package com.hcis.ipanther.common.dict.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcis.ipanther.common.dict.utils.DictUtils;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.web.controller.BaseController;

@Controller
@RequestMapping("common/dict")
public class DictionaryController extends BaseController{

	@RequestMapping("getEntryOptionsSelectedByParentValue")
	@ResponseBody
	public String getEntryOptionsSelectedByParentValue(DictEntry dictEntry){
		return DictionaryUtils.getEntryOptionsSelected(dictEntry.getDictTypeCode(), dictEntry.getParentValue(), dictEntry.getDictValue());
	}
	
	@RequestMapping("getEntryOptionsSelectedNotParent")
	@ResponseBody
	public String getEntryOptionsSelectedNotParent(DictEntry dictEntry){
		return DictionaryUtils.getEntryOptionsSelectedNotParent(dictEntry.getDictTypeCode(), dictEntry.getDictValue());
	}
	
	@RequestMapping("getAllParentValue")
	@ResponseBody
	public String getAllParentValue(DictEntry dictEntry){
		return DictionaryUtils.getAllParentValue(dictEntry.getDictTypeCode(), dictEntry.getDictValue());
	}
	
	@RequestMapping("getListElemtEntry")
	@ResponseBody
	public String getListElemtEntry(DictEntry dictEntry, HttpServletResponse response){
		if(dictEntry!=null){
			return this.renderText(DictUtils.getListSpanEntry(dictEntry.getDictTypeCode(), dictEntry.getDictValue()),response);
		}
		return null;
	}
}
