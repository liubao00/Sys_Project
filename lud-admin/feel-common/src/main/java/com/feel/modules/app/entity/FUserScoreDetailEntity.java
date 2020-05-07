package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分表流水详情
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-31 15:05:21
 */
@Data
@TableName("f_user_score_detail")
public class FUserScoreDetailEntity implements Serializable {
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
	 * 订单ID
	 */
	private Integer orderId;
	/**
	 * 用户当前可用积分
	 */
	private BigDecimal score;
	/**
	 * 用户当前冻结积分
	 */
	private BigDecimal freezeScore;
	/**
	 * 操作类型；1：增加 2：减少
	 */
	private Integer operateType;
	/**
	 * 本次操作积分
	 */
	private BigDecimal operateScore;
	/**
	 * 操作积分的业务类型
	 */
	private Integer detailType;
	/**
	 * 关联用户积分表
	 */
	private Integer userScoreId;
	/**
	 * 积分类型  1可用余额 2收益积分
	 */
	private Integer scoreType;
	/**
	 * 状态：1.冻结中，2.冻结失败返现 3.冻结失败扣除4.已完成
	 */
	private Integer status;
	/**
	 * 备注，业务单据号，奖励类别等
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 返现时间
	 */
	private Date backTime;

	/**
	 * 商品id
	 */
	private Integer productId;

	public FUserScoreDetailEntity() {}

	private FUserScoreDetailEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.orderId = builder.orderId;
		this.score = builder.score;
		this.freezeScore = builder.freezeScore;
		this.operateType = builder.operateType;
		this.operateScore = builder.operateScore;
		this.detailType = builder.detailType;
		this.userScoreId = builder.userScoreId;
		this.scoreType = builder.scoreType;
		this.status = builder.status;
		this.remark = builder.remark;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
		this.backTime = builder.backTime;
		this.productId = builder.productId;
	}

	public static Builder newFUserScoreDetailEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private Integer userId;
		private Integer orderId;
		private BigDecimal score;
		private BigDecimal freezeScore;
		private Integer operateType;
		private BigDecimal operateScore;
		private Integer detailType;
		private Integer userScoreId;
		private Integer scoreType;
		private Integer status;
		private String remark;
		private Date createTime;
		private Date updateTime;
		private Date backTime;
		private Integer productId;

		public Builder() {
		}

		public FUserScoreDetailEntity build() {
			return new FUserScoreDetailEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder orderId(Integer orderId) {
			this.orderId = orderId;
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

		public Builder operateType(Integer operateType) {
			this.operateType = operateType;
			return this;
		}

		public Builder operateScore(BigDecimal operateScore) {
			this.operateScore = operateScore;
			return this;
		}

		public Builder detailType(Integer detailType) {
			this.detailType = detailType;
			return this;
		}

		public Builder userScoreId(Integer userScoreId) {
			this.userScoreId = userScoreId;
			return this;
		}

		public Builder scoreType(Integer scoreType) {
			this.scoreType = scoreType;
			return this;
		}

		public Builder status(Integer status) {
			this.status = status;
			return this;
		}

		public Builder remark(String remark) {
			this.remark = remark;
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

		public Builder backTime(Date backTime) {
			this.backTime = backTime;
			return this;
		}

		public Builder productId(Integer productId) {
			this.productId = productId;
			return this;
		}
	}
}
