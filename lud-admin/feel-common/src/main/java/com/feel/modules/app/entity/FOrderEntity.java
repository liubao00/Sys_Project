package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 限购订单详情表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-26 08:49:46
 */
@Data
@TableName("f_order")
public class FOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号
	 */
	private String serialNo;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 商品名
	 */
	private String productName;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 复投次数
	 */
	private Integer times;
	/**
	 * 单品总价
	 */
	private BigDecimal totalPrice;
	/**
	 * 返还佣金
	 */
	private BigDecimal scoreBack;
	/**
	 * 支付时间
	 */
	private Date startTime;
	/**
	 * 取消时间
	 */
	private Date endTime;
	/**
	 * 商品状态 0待支付，1支付成功 2订单失效 3
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public FOrderEntity() {}

	private FOrderEntity(Builder builder) {
		this.id = builder.id;
		this.serialNo = builder.serialNo;
		this.userId = builder.userId;
		this.userName = builder.userName;
		this.productId = builder.productId;
		this.productName = builder.productName;
		this.picture = builder.picture;
		this.unitPrice = builder.unitPrice;
		this.times = builder.times;
		this.totalPrice = builder.totalPrice;
		this.scoreBack = builder.scoreBack;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.status = builder.status;
		this.remark = builder.remark;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static Builder newFOrderEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private String serialNo;
		private Integer userId;
		private String userName;
		private Integer productId;
		private String productName;
		private String picture;
		private BigDecimal unitPrice;
		private Integer times;
		private BigDecimal totalPrice;
		private BigDecimal scoreBack;
		private Date startTime;
		private Date endTime;
		private Integer status;
		private String remark;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public FOrderEntity build() {
			return new FOrderEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder serialNo(String serialNo) {
			this.serialNo = serialNo;
			return this;
		}

		public Builder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder productId(Integer productId) {
			this.productId = productId;
			return this;
		}

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder picture(String picture) {
			this.picture = picture;
			return this;
		}

		public Builder unitPrice(BigDecimal unitPrice) {
			this.unitPrice = unitPrice;
			return this;
		}

		public Builder times(Integer times) {
			this.times = times;
			return this;
		}

		public Builder totalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public Builder scoreBack(BigDecimal scoreBack) {
			this.scoreBack = scoreBack;
			return this;
		}

		public Builder startTime(Date startTime) {
			this.startTime = startTime;
			return this;
		}

		public Builder endTime(Date endTime) {
			this.endTime = endTime;
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
	}
}
