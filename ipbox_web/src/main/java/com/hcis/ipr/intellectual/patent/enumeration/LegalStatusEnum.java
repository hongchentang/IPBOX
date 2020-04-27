package com.hcis.ipr.intellectual.patent.enumeration;

/**
 * @author zhw
 * @date 2019/12/20
 **/
public enum LegalStatusEnum {

    /**
     * 专利的法律状态枚举
     */
    POSTPONE_APPLY("暂缓申请", "", 0),
    ACCEPTANCE("受理", "", 1),
    FIRST_TRIAL("初审", "", 2),
    CORRECTION("补正", "", 3),
    EXAMINE("审查", "", 4),
    FIRST_QUALIFICATION("初审合格", "", 5),
    FIRST_REPLY("初审答复", "", 6),
    OPEN("公开", "公开", 7),
    EXAMINATION("实审", "", 8),
    FIRST_PASS("一通", "", 9),
    SECOND_PASS("二通", "", 10),
    THIRD_PASS("三通", "", 11),
    FOURTH_PASS("四通", "", 12),
    FIFTH_PASS("五通", "", 13),
    SIXTH_PASS("六通", "", 14),
    SEVENTH_PASS("七通", "", 15),
    EIGHTH_PASS("八通", "", 17),
    NINTH_PASS("九通", "", 18),
    FIRST_REPAIR("一补", "", 19),
    SECOND_REPAIR("二补", "", 20),
    THIRD_REPAIR("三补", "", 21),
    DEEMED_WIDTH_DRAW("视为撤回", "视为撤回", 22),
    ACTIVE_WIDTH_DRAW("主动撤回", "主动撤回", 23),
    REJECT("驳回", "驳回", 24),
    REVIEW("复审", "", 25),
    INVALID("无效", "无效", 26),
    DEEMED_GIVE_UP("视为放弃", "视为放弃", 27),
    ACTIVE_GIVE_UP("主动放弃", "主动放弃", 28),
    AUTHORIZE("授权", "", 29),
    TO_BE_CERTIFIED("待领证", "", 30),
    KEEP("维持", "维持", 31),
    END("终止", "终止", 32),
    CLOSE_CASE("结案", "", 33),
    EXPIRATION("届满", "届满", 34),
    PCT_INTERNATIONAL_SEARCH("PCT国际检索", "", 35),
    INTERRUPT("中止", "中止", 36),
    PRESERVATION("保全", "保全", 37),
    LITIGATION("诉讼", "", 38),
    REGISTER("办理登记手续", "", 39),
    REVIEW_ACCEPTANCE("复审受理", "", 40),
    DEEMED_UN_PROPOSE("视为未提出", "", 41),
    NOTICE("公告", "公告", 42),
    INVALID_ACCEPTANCE("无效受理", "", 43),
    WAIT_EXAMINATION("等待实审", "", 44),
    PLEDGE("专利权质押合同登记", "专利权质押合同登记", 45);

    private String name;

    private String aliasName;

    private Integer val;

    public String getAliasName() {
        return aliasName;
    }

    public String getName() {
        return name;
    }

    public Integer getVal() {
        return val;
    }

    LegalStatusEnum(String name, String aliasName, int val) {
        this.name = name;
        this.aliasName = aliasName;
        this.val = val;
    }
}
