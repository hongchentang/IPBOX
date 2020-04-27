package com.hcis.ipr.cms.info.img.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipr.cms.info.img.service.ICmsInfoImgService;

@Controller
@RequestMapping("/cms/info/img")
public class CmsInfoImgController extends BaseController {

	@Autowired
	private ICmsInfoImgService cmsInfoImgService;
	
	
	
	
}
