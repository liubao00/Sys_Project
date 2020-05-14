package org.javaboy.vhr.model;

/**
 * 养老用户表
 */
public class YlUser {

    /** 主键ID */
    private Integer id ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private String createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private String updatedTime ;
    /** 用户ID */
    private Integer userId ;
    /** 用户名称 */
    private String userName ;
    /** 用户密码 */
    private String userPassword ;
    /** 用户状态;0；正常1：冻结 */
    private String userState ;
    /** TOKEN */
    private String token ;
    /** 手机号码 */
    private String phoneNum ;
    /** 收到推荐码获取的ID */
    private String inviteId ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }
}
