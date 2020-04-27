package com.hcis.ipr.quartz.legal;

import com.hcis.ipr.intellectual.patent.entity.Patent;
import com.hcis.ipr.intellectual.patent.service.PatentService;
import com.hcis.ipr.warehouse.patentHouse.entity.PatentMongo;
import com.hcis.ipr.warehouse.patentHouse.service.IPatentMongoService;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhw
 * @date 2019/9/30
 **/
public class LegalQuartzJob {

    protected Logger logger= LoggerFactory.getLogger(getClass());

    private static final Integer INVALID = 3;

    @Resource
    PatentService patentService;
    @Resource
    IPatentMongoService patentMongoService;

    public void run4UpdatePatentLegalStatus(){
        logger.debug("开始" + new Date().toString());

        //查询所有关联了MongoDB数据的专利数据 all
        List<Patent> patentList = patentService.listConcatMongoPatent();

        //取出所有的申请号，list转map
        Map<String, Patent> map = new HashMap<>();
        List<String> mongoPatentIdList = new ArrayList<>();
        for(Patent patent : patentList){
            String mongoPatentId = patent.getMongoPatentId();
            mongoPatentIdList.add(mongoPatentId);
            map.put(mongoPatentId, patent);
        }

        //查询所有的法律信息
        List<Document> legalList = patentMongoService.getLegalListByIds(mongoPatentIdList);

        //遍历法律信息信息找出已改变的法律状态
        List<Patent> updateList = new ArrayList<>();
        for(Document document : legalList){
           List documents = document.get("patent_legal_status", Document.class).get("legal_status_history", List.class);
           Object obj = documents.get(documents.size() - 1);
           String category = ((Document) obj).getString("category");
           String mongoPatentTd = document.getString("_id");
           Integer lastStatus = document.get("patent_legal_status", Document.class).getInteger("legal_status");
           Patent patent = map.get(mongoPatentTd);
           if(patent != null){
                convertLegalStatus(patent, category, lastStatus, updateList);
           }
        }

        //批量修改已改变的法律状态的数据
        if(updateList.size() > 0){
            patentService.updateBatch(updateList);
        }
        logger.debug("结束" + new Date().toString());
    }

    public void convertLegalStatus(Patent patent, String category, Integer lastStatus,List<Patent> updateList) {

        Integer resultCode = null;
        switch (category){
            case "驳回" :
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

        if(INVALID.equals(lastStatus)){
            resultCode = 26;
        }

        String pLegalStatus = patent.getLegalStatus();
        if(resultCode != null){
            String rCode = resultCode + "";
            if(!rCode.equals(pLegalStatus)){
                patent.setLegalStatus(rCode);
                updateList.add(patent);
            }
        }
    }

}
