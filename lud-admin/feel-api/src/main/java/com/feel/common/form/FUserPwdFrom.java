package com.feel.common.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@ApiModel(value = "修改用户信息")
public class FUserPwdFrom implements Serializable {
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
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码")
	@NotNull(message="验证码不能为空")
	private String code;

	public FUserPwdFrom() {}
}
