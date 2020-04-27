package com.hcis.ipr.intellectual.cost.enumeration;

/**
 * @author Administrator
 */

public enum GovernmentType {
	/**
	 *
	 */
	APPLICATION_FEE("申请费", 0), EXAMINATION_FEE("实质审查费", 1), REVIEW_FEE("复审费", 2), DESCRIPTIVE_MATTERS_CHANGE_FEE("著录事项变更费-发明人、申请人、专利权人的变更", 3), PRIORITY_CLAIM_FEE("优先权要求费/每项", 4), RIGHT_OF_REPLAY_FEE("恢复权利请求费", 5), NULLIFICATION_FEE("无效宣告请求费", 6), COMPULSORY_LICENSING_FEE("强制许可请求费", 7), COMPULSORY_LICENSING_AWARD_FEE("强制许可使用裁决请求费", 8), T4("印花税", 9), SURCHARGE("附加费", 10), T5("专利权评价报告请求费", 11), T6("实用新型专利检索报告费", 12), T7("中止费", 13), T8("公布印刷费", 14);

	// CANCEL_FEE("撤销请求费", 6),
	// PRINT_FEE("专利登记、印刷、印花费", 10),

	private String governmentTypeName;

	private int governmentType;

	GovernmentType(String governmentTypeName, int governmentType) {
		this.governmentTypeName = governmentTypeName;
		this.governmentType = governmentType;
	}

	public String getGovernmentTypeName() {
		return governmentTypeName;
	}

	public int getGovernmentType() {
		return governmentType;
	}

	public static GovernmentType valueOf(Integer type) {
		GovernmentType[] types = GovernmentType.values();
		for (GovernmentType gt : types) {
			if (gt.getGovernmentType() == type) {
				return gt;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		GovernmentType[] types = GovernmentType.values();
		StringBuilder stringBuffer = new StringBuilder();
		for (GovernmentType type : types) {
			stringBuffer.append(type.getGovernmentType()).append("：").append(type.getGovernmentTypeName()).append("，");
		}

		System.out.println(stringBuffer.toString());
	}
}
