package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 冲币记录表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-23 23:23:25
 */
@Data
@TableName("f_charge_record")
public class FChargeRecordEntity implements Serializable {
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
	 * 冲币个数
	 */
	private BigDecimal number;
	/**
	 * 积分数
	 */
	private BigDecimal score;
	/**
	 * 冲币hash值
	 */
	private String hash;
	/**
	 * 冲币账号
	 */
	private String account;
	/**
	 * 冲币凭证
	 */
	private String pic;
	/**
	 * 状态：1申请中 2审核通过完成 3审核不通过
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

	public FChargeRecordEntity() {}

	private FChargeRecordEntity(Builder builder) {
		setId(builder.id);
		setUserId(builder.userId);
		setNumber(builder.number);
		setScore(builder.score);
		setHash(builder.hash);
		setAccount(builder.account);
		setPic(builder.pic);
		setState(builder.state);
		setCreateTime(builder.createTime);
		setUpdateTime(builder.updateTime);
	}


	public static final class Builder {
		private Integer id;
		private Integer userId;
		private BigDecimal number;
		private BigDecimal score;
		private String hash;
		private String account;
		private String pic;
		private Integer state;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public Builder id(Integer val) {
			id = val;
			return this;
		}

		public Builder userId(Integer val) {
			userId = val;
			return this;
		}

		public Builder number(BigDecimal val) {
			number = val;
			return this;
		}

		public Builder score(BigDecimal val) {
			score = val;
			return this;
		}

		public Builder hash(String val) {
			hash = val;
			return this;
		}

		public Builder account(String val) {
			account = val;
			return this;
		}

		public Builder pic(String val) {
			pic = val;
			return this;
		}

		public Builder state(Integer val) {
			state = val;
			return this;
		}

		public Builder createTime(Date val) {
			createTime = val;
			return this;
		}

		public Builder updateTime(Date val) {
			updateTime = val;
			return this;
		}

		public FChargeRecordEntity build() {
			return new FChargeRecordEntity(this);
		}
	}
}
