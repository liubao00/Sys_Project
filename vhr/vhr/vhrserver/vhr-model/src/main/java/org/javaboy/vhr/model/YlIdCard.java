package org.javaboy.vhr.model;

import java.math.BigDecimal;

/**
 * 养老卡
 */
public class YlIdCard {
    /** ID;ID号唯一 */
    private Integer id ;
    /** 类型;0：正常注册；1：拼团 */
    private String type ;
    /** 备注名;卡别名 */
    private String name ;
    /** 是否默认账号;1；默认 */
    private String isdefult ;
    /** 创建时间 */
    private String createdTime ;
    /** 创建方式;0：推荐注册；1：系统生成 */
    private String createdWay ;
    /** 用户ID;用户id */
    private Integer userId ;
    /** 补贴 */
    private BigDecimal subsidies ;
    /** 奖励 */
    private BigDecimal reward ;
    /** 养老基金 */
    private BigDecimal persion ;
    /** 大病互助 */
    private BigDecimal seriousHelp ;
    /** 创业基金 */
    private BigDecimal ventureCapital ;
    /** 公益金 */
    private BigDecimal publicWelfFund ;
    /** 状态;0:正常；1：冻结 */
    private String state ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsdefult() {
        return isdefult;
    }

    public void setIsdefult(String isdefult) {
        this.isdefult = isdefult;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedWay() {
        return createdWay;
    }

    public void setCreatedWay(String createdWay) {
        this.createdWay = createdWay;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getSubsidies() {
        return subsidies;
    }

    public void setSubsidies(BigDecimal subsidies) {
        this.subsidies = subsidies;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public BigDecimal getPersion() {
        return persion;
    }

    public void setPersion(BigDecimal persion) {
        this.persion = persion;
    }

    public BigDecimal getSeriousHelp() {
        return seriousHelp;
    }

    public void setSeriousHelp(BigDecimal seriousHelp) {
        this.seriousHelp = seriousHelp;
    }

    public BigDecimal getVentureCapital() {
        return ventureCapital;
    }

    public void setVentureCapital(BigDecimal ventureCapital) {
        this.ventureCapital = ventureCapital;
    }

    public BigDecimal getPublicWelfFund() {
        return publicWelfFund;
    }

    public void setPublicWelfFund(BigDecimal publicWelfFund) {
        this.publicWelfFund = publicWelfFund;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
