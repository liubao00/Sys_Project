package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:47:00
 */
@Data
@TableName("f_user")
public class FUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 登录名
	 */
	@ApiModelProperty(value = "登录名,目前是手机号码")
	private String username;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "登录密码")
	private String password;
	/**
	 * 支付密码
	 */
	@ApiModelProperty(value = "支付密码")
	private String payPassword;
	/**
	 * 用户状态  1正常  2冻结 3禁用
	 */
	@ApiModelProperty(value = "用户状态  1正常  2冻结 3禁用")
	private Integer status;
	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id")
	private Integer roleId;
	/**
	 * 邀请码
	 */
	@ApiModelProperty(value = "邀请码")
	private String invitationCode;
	/**
	 * 身份证
	 */
	@ApiModelProperty(value = "身份证")
	private String idCard;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名")
	private String realName;
	/**
	 * 身份证正面
	 */
	@ApiModelProperty(value = "身份证正面")
	private String idcardFront;
	/**
	 * 身份证反面
	 */
	@ApiModelProperty(value = "身份证反面")
	private String idcardReverse;
	/**
	 * 性别 1男 2女
	 */
	@ApiModelProperty(value = "性别 1男 2女")
	private Integer gender;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	/**
	 * 城市
	 */
	@ApiModelProperty(value = "城市")
	private String city;
	/**
	 * usdt钱包地址
	 */
	@ApiModelProperty(value = "sdt钱包地址")
	private String addressUsdt;
	/**
	 * eth钱包地址
	 */
	@ApiModelProperty(value = "eth钱包地址")
	private String addressEth;
	/**
	 * 审核状态 0未审核 1审核成功 2审核拒绝
	 */
	@ApiModelProperty(value = "审核状态 0未审核 1审核成功 2审核拒绝")
	private Integer type;
	/**
	 * 上一次登录的ip地址
	 */
	@ApiModelProperty(value = "上一次登录的ip地址")
	private String lastLoginIp;
	/**
	 * 上一次登录时间
	 */
	@ApiModelProperty(value = "上一次登录时间")
	private Date lastLoginTime;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**
	 * 是否复投  1:复投 0:不复投
	 */
	@ApiModelProperty(value = "是否复投  1:复投 0:不复投")
	private Integer automaticOrder;

	public FUserEntity() {}
}
