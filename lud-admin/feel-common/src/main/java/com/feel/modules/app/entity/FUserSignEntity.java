package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户签到表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-02 22:48:16
 */
@Data
@TableName("f_user_sign")
public class FUserSignEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 签到月份 2019-08a
	 */
	private String month;
	/**
	 * 每天签到结果 数组[1,0,1] 1表示签到 0表示未签到
	 */
	private String results;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public FUserSignEntity() {}
}
