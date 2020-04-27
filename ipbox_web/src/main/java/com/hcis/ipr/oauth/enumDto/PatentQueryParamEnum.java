package com.hcis.ipr.oauth.enumDto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/11/22
 **/
public enum PatentQueryParamEnum {
    /**
     *
     */
    NAME("名称","patentName"),
    DIGEST("摘要","digest"),
    PATENT_TYPE("专利类型","patentType"),
    APP_NUMBER("申请号","appNumber"),
    APP_DATE_START("申请日","appDateStart"),
    APP_DATE_END("申请日","appDateEnd"),
    PUBLICATION_NUMBER("公开（公告）号","publicationNumber"),
    PUBLICATION_DATE_START("公开（公告）日","publicationDateStart"),
    PUBLICATION_DATE_END("公开（公告）日","publicationDateEnd"),
    PATENTEE("申请（专利权）人","patentee"),
    INVENTOR("发明（设计）人","inventor"),
    MAIN_CATEGORY("主分类号","mainCategory"),
    CATEGORY("分类号","category"),
    AGENCY("专利代理机构","agency"),
    AGENTS("代理人","agents"),
    PRIORITY("优先权","priority"),
    RIGHT_CLAIMING_DOC("权利要求书","rightClaimingDoc"),
    PRINCIPAL("主权项","principal"),
    SOLE_AUTHORITY("独权限","soleAuthority"),
    INSTRUCTIONS("说明书","instructions"),
    ADDRESS("地址","address"),
    PROVINCE("省","province"),
    CITY("市","city"),
    AREA("区","area"),
    APPLY_COUNTRY_CODE("申请国代码","applyCountryCode"),
    PUBLIC_COUNTRY_CODE("公开国代码","publicCountryCode"),
    COUNTRY_PROVINCE_CODE("国省代码","countryProvinceCode"),
    KEYWORD("关键词","keyword"),
    NAME_KEYWORD("名称关键词","nameKeyword"),
    SOLE_AUTHORITY_KEYWORD("独权关键词","soleAuthorityKeyword"),
    BACKGROUND_KEYWORD("背景关键词","backgroundKeyword"),
    APPLICATION_ON_THE_SAME_DAY("同日申请","applicationOnTheSameDay"),
    EXAMINER("审查员","examiner"),
    SOURCE_OF_APPLICATION("申请来源","sourceOfApplication"),
    INTERNATIONAL_APPLICATION("国际申请","internationalApplication"),
    INTERNATIONAL_PUBLICATION("国际公布","internationalPublication"),
    ENTER_COUNTRY_DATE_START("进入国家日","enterCountryDateStart"),
    ENTER_COUNTRY_DATE_END("进入国家日","enterCountryDateEnd"),
    LEGAL_STATUS("法律状态","legalStatus"),
    PATENT_RIGHT_STATUS("专利权状态","patentRightStatus"),
    PATENT_RIGHT_STATUS_CDE("专利权状态待代码","legalStatusCode"),
    PCT_APPOINT_COUNTRY("PCT指定国家","pctAppointCountry"),
    DB_NAME("DB_NAME","dbName"),
    CLASSIFICATION_OF_NATIONAL_ECONOMY("国名经济分类","classificationOfNationalEconomy"),
    APPLYER("专利权人","applyer"),
    HISTORY_APPLYER("历史专利权人","historyApplyer"),
    AUTHORIZATION_DATE_START("授权日","authorizationDateStart"),
    AUTHORIZATION_DATE_END("授权日","authorizationDateEnd"),
    EXPIRATION_DATE_START("失效日","expirationDateStart"),
    EXPIRATION_DATE_END("失效日","expirationDateEnd"),
    APPLYER_TYPE("申请人类型","applyerType"),
    RIGHT_CLAIMING_DOC_TYPE("权利要求书类型","rightClaimingDocType"),
    PRIORITY_NUMBER("优先权号","priorityNumber"),
    FIRST_PRIORITY("最早优先权","firstPriority"),
    PRIORITY_COUNTRY("优先权国家","priorityCountry"),
    INSTRUCTIONS_PAGES("说明书页数","instructionsPages"),
    AUTHORIZATION_DURATION("授权时长","authorizationDuration"),
    IS_ADVANCE_AUTHORIZATION("是否提前公开","isAdvanceAuthorization"),
    DECLASSIFICATION_ANNOUNCEMENT_DATE_START("解密公告日","declassificationAnnouncementDateStart"),
    DECLASSIFICATION_ANNOUNCEMENT_DATE_END("解密公告日","declassificationAnnouncementDateEnd"),
    CATEGORY_NUMBER("分类号小类数","categoryNumber"),
    INVENTOR_NUMBER("发明人数","inventorNumber"),
    RIGHT_NUMBER("权利要求数","rightNumber"),
    SOLE_AUTHORITY_NUMBER("独权数","soleAuthorizationNumber"),
    EXPEDIENT_NUMBER("从权数","expedientNumber"),
    TRANSFER_NUMBER("专利权转移次数","transferNumber"),
    APPLICATION_TRANSFER_NUMBER("申请权转移次数","applicationTransferNumber"),
    PERMIT_NUMBER("许可次数","permitNumber"),
    PLEDGE_NUMBER("质押次数","pledgeNumber"),
    PRESERVATION_NUMBER("保全次数","preservationNumber"),
    CITATIONS_NUMBER("引证总次数","citationsNumber"),
    PATENT_CITATIONS_NUMBER("专利引证次数","patentCitationsNumber"),
    EXCLUDE_PATENT_CITATION_NUMBER("非专利引证次数","excludePatentCitationNumber"),
    ARE_QUOTE_NUMBER("被引证次数","areQuoteNumber"),
    REVIEW_NUMBER("复审次数","reviewNumber"),
    INVALID_NUMBER("无效次数","invalidNumber"),
    SENTENCE_NUMBER("判决次数","sentenceNumber"),
    COUNTRY_CODE("国家代码","countryCode"),
    COUNTRY_NAME("国家名","countryName"),
    PROVINCE_NAME("省名","provinceName"),
    CITATIONS_CODE("引证号","citationsCode"),
    CITATIONS_CATEGORY_CODE("引证分类号","citationsCategoryCode"),
    CITATIONS_APPLYER("引证申请人","citationsApplyer"),
    CITATIONS_COUNTRY("引证国家","citationsCountry"),
    NON_PATENT_CITATION("非专利引证","nonPatentCitation"),
    QUOTED_NUMBER("被引证号","quitedNumber"),
    CITED_COUNTRY("被引证国家","citedCountry"),
    CLASSIFICATION_DEPARTMENT("分类号部","classificationDepartment"),
    CLASSIFICATION_BIG_DEPARTMENT("分类号大部","classificationBigDepartment"),
    CLASSIFICATION_SMALL_DEPARTMENT("分类号小部","classificationSmallDepartment"),
    CLASSIFICATION_BIG_GROUP("分类号大组","classificationBigGroup"),
    CLASSIFICATION_SMALL_GROUP("分类号小组","classificationSmallGroup");

    private String name;

    private String key;

    PatentQueryParamEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public static Map<String, PatentQueryParamEnum> toMap(){
        PatentQueryParamEnum[] paramEnums = PatentQueryParamEnum.values();
        Map<String, PatentQueryParamEnum> map = new HashMap<>(paramEnums.length);
        for(PatentQueryParamEnum paramEnum : paramEnums){
            map.put(paramEnum.getKey(), paramEnum);
        }

        return map;
    }
}
