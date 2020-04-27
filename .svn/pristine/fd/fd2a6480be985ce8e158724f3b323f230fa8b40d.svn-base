package com.hcis.ipr.intellectual.patent.service;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.intellectual.patent.entity.PatentAttachment;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PatentAttachmentService extends IBaseService<PatentAttachment>{

    /**
     * 根据ids查询专利的所有附件
     * @param appNumbers
     * @return
     */
    Map<String, Map<String, List<PatentAttachment>>> selectByPatentAppNumbers(List<String> appNumbers);

    /**
     * 查询这个专利的所有附件
     * @param appNumber
     * @return
     */
    Map<String, List<PatentAttachment>> selectPatentAttachmentsMap(String appNumber);
}
