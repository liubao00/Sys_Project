package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:14:55
 */
@Data
@TableName("f_user_score")
public class FUserScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 关联用户ID
	 */
	private Integer userId;
	/**
	 * 可用投资余额
	 */
	private BigDecimal score;
	/**
	 * 冻结投资余额
	 */
	private BigDecimal freezeScore;
	/**
	 * 收益余额
	 */
	private BigDecimal earningsScore;
	/**
	 * 冻结收益余额
	 * */
	private BigDecimal freezeEarnings;
	/**
	 * 积分类型1：佣金2：限购3：商城
	 */
	private Integer scoreType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public FUserScoreEntity() {}

	private FUserScoreEntity(Builder builder) {
		setId(builder.id);
		setUserId(builder.userId);
		setScore(builder.score);
		setFreezeScore(builder.freezeScore);
		setEarningsScore(builder.earningsScore);
		setFreezeEarnings(builder.freezeEarnings);
		setScoreType(builder.scoreType);
		setCreateTime(builder.createTime);
		setUpdateTime(builder.updateTime);
	}

	public static Builder newFUserScoreEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private Integer userId;
		private BigDecimal score;
		private BigDecimal freezeScore;
		private BigDecimal earningsScore;
		private BigDecimal freezeEarnings;
		private Integer scoreType;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public FUserScoreEntity build() {
			return new FUserScoreEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder score(BigDecimal score) {
			this.score = score;
			return this;
		}

		public Builder freezeScore(BigDecimal freezeScore) {
			this.freezeScore = freezeScore;
			return this;
		}

		public Builder earningsScore(BigDecimal earningsScore) {
			this.earningsScore = earningsScore;
			return this;
		}

		public Builder freezeEarnings(BigDecimal val) {
			freezeEarnings = val;
			return this;
		}

		public Builder scoreType(Integer scoreType) {
			this.scoreType = scoreType;
			return this;
		}

		public Builder createTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder updateTime(Date updateTime) {
			this.updateTime = updateTime;
			return this;
		}
	}
}
