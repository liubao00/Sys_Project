package org.javaboy.vhr.model;

import java.util.Date;

/**
 * @author jianfengLIan
 * @time 2020年5月13日14:45:07
 * @deprecated 养老用户模型
 */
public class YlUser {
    /** 主键ID */
    private String id ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;
    /** 用户ID */
    private String userId ;
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

    public YlUser() {
    }

    public YlUser(String id, String createdBy, Date createdTime, String updatedBy, Date updatedTime, String userId, String userName, String userPassword, String userState, String token, String phoneNum, String inviteId) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userState = userState;
        this.token = token;
        this.phoneNum = phoneNum;
        this.inviteId = inviteId;
    }

    /** 主键ID */
    public String getId(){
        return this.id;
    }
    /** 主键ID */
    public void setId(String id){
        this.id = id;
    }
    /** 创建人 */
    public String getCreatedBy(){
        return this.createdBy;
    }
    /** 创建人 */
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    /** 创建时间 */
    public Date getCreatedTime(){
        return this.createdTime;
    }
    /** 创建时间 */
    public void setCreatedTime(Date createdTime){
        this.createdTime = createdTime;
    }
    /** 更新人 */
    public String getUpdatedBy(){
        return this.updatedBy;
    }
    /** 更新人 */
    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }
    /** 更新时间 */
    public Date getUpdatedTime(){
        return this.updatedTime;
    }
    /** 更新时间 */
    public void setUpdatedTime(Date updatedTime){
        this.updatedTime = updatedTime;
    }
    /** 用户ID */
    public String getUserId(){
        return this.userId;
    }
    /** 用户ID */
    public void setUserId(String userId){
        this.userId = userId;
    }
    /** 用户名称 */
    public String getUserName(){
        return this.userName;
    }
    /** 用户名称 */
    public void setUserName(String userName){
        this.userName = userName;
    }
    /** 用户密码 */
    public String getUserPassword(){
        return this.userPassword;
    }
    /** 用户密码 */
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    /** 用户状态;0；正常1：冻结 */
    public String getUserState(){
        return this.userState;
    }
    /** 用户状态;0；正常1：冻结 */
    public void setUserState(String userState){
        this.userState = userState;
    }
    /** TOKEN */
    public String getToken(){
        return this.token;
    }
    /** TOKEN */
    public void setToken(String token){
        this.token = token;
    }
    /** 手机号码 */
    public String getPhoneNum(){
        return this.phoneNum;
    }
    /** 手机号码 */
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
    /** 收到推荐码获取的ID */
    public String getInviteId(){
        return this.inviteId;
    }
    /** 收到推荐码获取的ID */
    public void setInviteId(String inviteId){
        this.inviteId = inviteId;
    }
}
