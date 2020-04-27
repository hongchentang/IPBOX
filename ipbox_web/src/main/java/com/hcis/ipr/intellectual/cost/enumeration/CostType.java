package com.hcis.ipr.intellectual.cost.enumeration;

public enum CostType {
    /**
     *
     */
    AGENCY_FEE("代理服务费", "", 0),
    ANNUAL_FEE("专利年费", "年费",1),
    GOVERNMENT_FEE("专利官费", "官费",2),
    OTHER_FEE("其他费用", "", 3);

    private String typeName;

    private String otherName;

    private int type;

    CostType(String typeName, String otherName,int type) {
        this.typeName = typeName;
        this.otherName = otherName;
        this.type = type;
    }

    public String getOtherName() {
        return otherName;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getType() {
        return type;
    }

    public static CostType valueOf(Integer type){
        CostType[] costTypes = CostType.values();
        for(CostType ct : costTypes){
            if (ct.getType() == type) {
                return ct;
            }
        }
        throw new IllegalArgumentException("没有此类型的费用");
    }
}
