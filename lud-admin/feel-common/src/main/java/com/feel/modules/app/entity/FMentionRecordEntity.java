package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 提币记录表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:04:31
 */
@Data
@TableName("f_mention_record")
public class FMentionRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 提现的积分
	 */
	private BigDecimal score;
	/**
	 * 提币个数
	 */
	private BigDecimal number;
	/**
	 * 手续费
	 */
	private BigDecimal fee;
	/**
	 * 提币hash值
	 */
	private String hash;
	/**
	 * 提现地址
	 */
	private String account;
	/**
	 * 类型：1.本金提现  2.收益提现
	 */
	private Integer type;
	/**
	 * 状态：1：申请中 2:审核通过,提币中 3提币完成 4审核不通过
	 */
	private Integer state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public FMentionRecordEntity() {}
}
