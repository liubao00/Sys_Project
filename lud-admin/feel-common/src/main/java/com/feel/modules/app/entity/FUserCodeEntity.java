package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-27 21:13:45
 */
@Data
@TableName("f_user_code")
public class FUserCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 手机号
	 */
	private String username;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 类型   1：注册   2：修改
	 */
	private Integer type;
	/**
	 * 过期时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public FUserCodeEntity() {}
}
