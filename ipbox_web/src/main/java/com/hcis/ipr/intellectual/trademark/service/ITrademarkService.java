package com.hcis.ipr.intellectual.trademark.service;

import com.hcis.ipanther.core.service.IBaseService;

import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.trademark.entity.Trademark;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ITrademarkService extends IBaseService<Trademark>{

    /**
     * 查询列表
     * @param searchParam
     * @return
     */
    List<Trademark> getList(SearchParam searchParam);

    /**
     * 上传保存商标图
     * @param trademark
     * @param file
     */
    void saveImage(Trademark trademark, MultipartFile file) throws IOException;

    /**
     * 上传保存附件
     * @param trademark
     * @param file
     */
    void saveFile(Trademark trademark, MultipartFile file) throws IOException;
}
