package com.hcis.ipr.intellectual.patent.enumeration;

public enum AttachmentEnum {

    /**
     * 申请原文件
     */
    SOURCE_FILE("sourceFile", 0),
    /**
     * 受理通知书
     */
    AUTHORIZATION_FILE("authorizationFile", 1),
    /**
     * 专利证书
     */
   PATENT_FILE("patentFile", 2),
    /**
     * 检索报告
     */
    SEARCH_FILE("searchFile", 3),
    /**
     * 代理合同
     */
    AGENCY_FILE("agencyFile", 4);


    private String typeName;

    private int type;

    AttachmentEnum(String typeName, int type) {
        this.typeName = typeName;
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getType() {
        return type;
    }

    public static AttachmentEnum valueOf(int type){
        AttachmentEnum[] attachmentEnums = AttachmentEnum.values();
        for(AttachmentEnum attachmentEnum : attachmentEnums){
            if(attachmentEnum.getType() == type){
                return attachmentEnum;
            }
        }

        return null;
    }
}
