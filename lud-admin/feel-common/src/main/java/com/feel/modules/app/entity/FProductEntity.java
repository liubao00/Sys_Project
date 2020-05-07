package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 15:58:09
 */
@Data
@TableName("f_product")
public class FProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 排单名称
	 */
	private String name;
	/**
	 * 排单图片
	 */
	private String pic;
	/**
	 * 排单金额
	 */
	private BigDecimal score;
	/**
	 * 排单商品状态 1正常 2已售完 0逻辑删除
	 */
	private Integer status;
	/**
	 * 排单商品描述
	 */
	private String remark;
	/**
	 * 规格描述
	 */
	private String specificationDesc;
	/**
	 * 规格
	 */
	private Integer specification;
	/**
	 * 静态奖励百分比
	 */
	private BigDecimal singleBack;
	/**
	 * 累计奖励百分比
	 */
	private BigDecimal doubleBack;
	/**
	 * 上限奖励百分比
	 */
	private BigDecimal maxBack;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public FProductEntity() {}

	private FProductEntity(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.pic = builder.pic;
		this.score = builder.score;
		this.status = builder.status;
		this.remark = builder.remark;
		this.specificationDesc = builder.specificationDesc;
		this.specification = builder.specification;
		this.singleBack = builder.singleBack;
		this.doubleBack = builder.doubleBack;
		this.maxBack = builder.maxBack;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static Builder newFProductEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private String name;
		private String pic;
		private BigDecimal score;
		private Integer status;
		private String remark;
		private String specificationDesc;
		private Integer specification;
		private BigDecimal singleBack;
		private BigDecimal doubleBack;
		private BigDecimal maxBack;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public FProductEntity build() {
			return new FProductEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder pic(String pic) {
			this.pic = pic;
			return this;
		}

		public Builder score(BigDecimal score) {
			this.score = score;
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

		public Builder specificationDesc(String specificationDesc) {
			this.specificationDesc = specificationDesc;
			return this;
		}

		public Builder specification(Integer specification) {
			this.specification = specification;
			return this;
		}

		public Builder singleBack(BigDecimal singleBack) {
			this.singleBack = singleBack;
			return this;
		}

		public Builder doubleBack(BigDecimal doubleBack) {
			this.doubleBack = doubleBack;
			return this;
		}

		public Builder maxBack(BigDecimal maxBack) {
			this.maxBack = maxBack;
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
