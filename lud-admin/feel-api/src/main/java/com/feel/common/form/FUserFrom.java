package com.feel.common.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 前端用戶表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-25 14:35:00
 */
@Data
@ApiModel(value = "用戶表")
public class FUserFrom implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	@NotBlank(message="用户名不能为空")
	private String username;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	@NotBlank(message="密码不能为空")
	private String password;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名")
//	@NotBlank(message="真实姓名不能为空")
	private String realName;
	/**
	 * 支付密码
	 */
	private String payPassword;

	/**
	 * 角色id
	 */
	private Integer roleId;
	/**
	 * 邀请码
	 */
	@ApiModelProperty(value = "邀请码")
	@NotNull(message="邀请码不能为空")
	private String invitationCode;

	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码")
	@NotNull(message="验证码不能为空")
	private String code;


	public FUserFrom() {}
}
