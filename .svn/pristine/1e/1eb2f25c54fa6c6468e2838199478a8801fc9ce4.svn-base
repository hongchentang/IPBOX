package com.hcis.ipr.intellectual.cost.enumeration;

/**
 * @author zhw
 */

public enum  AnnualType {

    /**
     * 专利年费
     */
    ANNUAL_1_FEE("第1年年费", 0),
    ANNUAL_2_FEE("第2年年费", 1),
    ANNUAL_3_FEE("第3年年费", 2),
    ANNUAL_4_FEE("第4年年费", 3),
    ANNUAL_5_FEE("第5年年费", 4),
    ANNUAL_6_FEE("第6年年费", 5),
    ANNUAL_7_FEE("第7年年费", 6),
    ANNUAL_8_FEE("第8年年费", 7),
    ANNUAL_9_FEE("第9年年费", 8),
    ANNUAL_10_FEE("第10年年费", 9),
    ANNUAL_11_FEE("第11年年费", 10),
    ANNUAL_12_FEE("第12年年费", 11),
    ANNUAL_13_FEE("第13年年费", 12),
    ANNUAL_14_FEE("第14年年费", 13),
    ANNUAL_15_FEE("第15年年费", 14),
    ANNUAL_16_FEE("第16年年费", 15),
    ANNUAL_17_FEE("第17年年费", 16),
    ANNUAL_18_FEE("第18年年费", 17),
    ANNUAL_19_FEE("第19年年费", 18),
    ANNUAL_20_FEE("第20年年费", 19);


    private String typeName;

    private Integer type;

    AnnualType(String typeName, Integer type) {
        this.typeName = typeName;
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getType() {
        return type;
    }

    public static AnnualType valueOf(Integer type){
        AnnualType[] types = AnnualType.values();
        for(AnnualType annualType : types){
            if(annualType.getType().equals(type)){
                return annualType;
            }
        }

        throw new IllegalArgumentException("没有此类型年费");
    }
}
