package com.hcis.ipr.intellectual.trademark.service.impl;

import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.trademark.dao.TrademarkDao;
import com.hcis.ipr.intellectual.trademark.entity.Trademark;
import com.hcis.ipr.intellectual.trademark.service.ITrademarkService;
import com.hcis.items.service.ItemsLibraryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service("trademarkService")
public class TrademarkServiceImpl extends BaseServiceImpl<Trademark> implements ITrademarkService {

	@Autowired
	private TrademarkDao trademarkDao;

	@Autowired
    private IUserService userService;

    @Autowired
    private ItemsLibraryService itemsLibraryService;

    @Override
	public MyBatisDao getBaseDao() {
		return trademarkDao;
	}

    @Override
    public List<Trademark> getList(SearchParam searchParam) {

        searchParam.getParamMap().put("deptIds", userService.getDeptIdsByUserId());
        searchParam.getParamMap().put("searchUserId", userService.getRoleUserId());

        return trademarkDao.selectBySearchParam(searchParam);
    }

    @Override
    public void saveImage(Trademark trademark, MultipartFile file) throws IOException {
        // 保存商标图
        Map<String, String> map = new HashMap<>();
        String reStr = itemsLibraryService.saveFile(map, file, "attachment.default.fileTypes.image",
                "trademark/registeredImage");
        if (StringUtils.isBlank(reStr) && map.size() > 0) {
            trademark.setRegisteredImage(JsonUtil.toJson(map));
        }
    }

    @Override
    public void saveFile(Trademark trademark, MultipartFile file) throws IOException {
        // 保存证书
        Map<String, String> map = new HashMap<>();
        String reStr = itemsLibraryService.saveFile(map, file, "attachment.default.fileTypes",
                "trademark/trademarkFile");
        if (StringUtils.isBlank(reStr) && map.size() > 0) {
            trademark.setTrademarkFile(JsonUtil.toJson(map));
        }
    }
}
