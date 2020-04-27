package com.hcis.ipr.attachment.web;

import com.hcis.ipr.common.utils.BaseApi;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.items.service.ItemsLibraryService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhw
 * @date 2019/11/7
 **/

@RequestMapping("/attachment")
@Controller
public class AttachmentControllerV2 extends BaseController {

    @Autowired
    private ItemsLibraryService itemsLibraryService;

    @RequestMapping("/upload")
    public BaseApi upload(HttpServletRequest request){
        BaseApi api = new BaseApi();
        try {

        }catch (Exception e){

        }
        return api;
    }
}
