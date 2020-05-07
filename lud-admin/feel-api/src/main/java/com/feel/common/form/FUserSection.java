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
@ApiModel(value = "用户层级下级用户分区")
public class FUserSection implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "用户Id")
	@NotNull(message="用户Id不能为空")
	private Integer userId;


	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "区间")
	@NotNull(message="用户区间不能为空")
	private Integer section;


	public FUserSection() {}
}
