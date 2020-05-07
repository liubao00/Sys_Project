package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 复投次数表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-29 15:34:27
 */
@Data
@TableName("f_repeat")
public class FRepeatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增
	 */
	@TableId
	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 产品ID
	 */
	private Integer productId;
	/**
	 * 复投次数
	 */
	private Integer times;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTiem;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 记录每轮返现次数
	 */

	private Integer countBack;

	public FRepeatEntity() {}

	private FRepeatEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.productId = builder.productId;
		this.times = builder.times;
		this.remark = builder.remark;
		this.createTiem = builder.createTiem;
		this.updateTime = builder.updateTime;
		this.countBack = builder.countBack;
	}

	public static Builder newFRepeatEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private Integer userId;
		private Integer productId;
		private Integer times;
		private String remark;
		private Date createTiem;
		private Date updateTime;
		private Integer countBack;

		public Builder() {
		}

		public FRepeatEntity build() {
			return new FRepeatEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder productId(Integer productId) {
			this.productId = productId;
			return this;
		}

		public Builder times(Integer times) {
			this.times = times;
			return this;
		}

		public Builder remark(String remark) {
			this.remark = remark;
			return this;
		}

		public Builder createTiem(Date createTiem) {
			this.createTiem = createTiem;
			return this;
		}

		public Builder updateTime(Date updateTime) {
			this.updateTime = updateTime;
			return this;
		}

		public Builder countBack(Integer countBack) {
			this.countBack = countBack;
			return this;
		}
	}
}
