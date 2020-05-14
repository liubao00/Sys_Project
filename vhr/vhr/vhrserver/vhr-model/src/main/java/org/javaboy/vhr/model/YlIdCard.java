package org.javaboy.vhr.model;

import java.util.Date;
/**
        * @author jianfengLIan
        * @time 2020年5月13日14:45:07
        * @deprecated id卡号模型
 */
public class YlIdCard {
    /** ID;ID号唯一 */
    private String id ;
    /** 类型;0：正常注册；1：拼团 */
    private String type ;
    /** 备注名;卡别名 */
    private String name ;
    /** 是否默认账号;1；默认 */
    private String isdefult ;
    /** 创建时间 */
    private Date createdTime ;
    /** 创建方式;0：推荐注册；1：系统生成 */
    private String createdWay ;
    /** 用户ID;用户id */
    private String userId ;
    /** 补贴 */
    private Double subsidies ;
    /** 奖励 */
    private Double reward ;
    /** 养老基金 */
    private Double persion ;
    /** 大病互助 */
    private Double seriousHelp ;
    /** 创业基金 */
    private Double ventureCapital ;
    /** 公益金 */
    private Double publicWelfFund ;
    /** 状态;0:正常；1：冻结 */
    private String state ;

    public YlIdCard() {

    }

    public YlIdCard(String id, String type, String name, String isdefult, Date createdTime, String createdWay, String userId, Double subsidies, Double reward, Double persion, Double seriousHelp, Double ventureCapital, Double publicWelfFund, String state) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.isdefult = isdefult;
        this.createdTime = createdTime;
        this.createdWay = createdWay;
        this.userId = userId;
        this.subsidies = subsidies;
        this.reward = reward;
        this.persion = persion;
        this.seriousHelp = seriousHelp;
        this.ventureCapital = ventureCapital;
        this.publicWelfFund = publicWelfFund;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedWay() {
        return createdWay;
    }

    public void setCreatedWay(String createdWay) {
        this.createdWay = createdWay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getSubsidies() {
        return subsidies;
    }

    public void setSubsidies(Double subsidies) {
        this.subsidies = subsidies;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Double getPersion() {
        return persion;
    }

    public void setPersion(Double persion) {
        this.persion = persion;
    }

    public Double getSeriousHelp() {
        return seriousHelp;
    }

    public void setSeriousHelp(Double seriousHelp) {
        this.seriousHelp = seriousHelp;
    }

    public Double getVentureCapital() {
        return ventureCapital;
    }

    public void setVentureCapital(Double ventureCapital) {
        this.ventureCapital = ventureCapital;
    }

    public Double getPublicWelfFund() {
        return publicWelfFund;
    }

    public void setPublicWelfFund(Double publicWelfFund) {
        this.publicWelfFund = publicWelfFund;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
