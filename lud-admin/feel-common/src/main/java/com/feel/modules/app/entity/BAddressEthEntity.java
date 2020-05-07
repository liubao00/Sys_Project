package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * eth地址池
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-31 01:39:07
 */
@Data
@TableName("b_address_eth")
public class BAddressEthEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 星空来客人
	 */
	private Integer value;
	/**
	 * 0:未使用 1已使用
	 */
	private String status;
	/**
	 * 备注
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

	public BAddressEthEntity() {}

	private BAddressEthEntity(Builder builder) {
		setId(builder.id);
		setAddress(builder.address);
		setValue(builder.value);
		setStatus(builder.status);
		setRemark(builder.remark);
		setCreateTime(builder.createTime);
		setUpdateTime(builder.updateTime);
	}


	public static final class Builder {
		private Integer id;
		private String address;
		private Integer value;
		private String status;
		private String remark;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public Builder id(Integer val) {
			id = val;
			return this;
		}

		public Builder address(String val) {
			address = val;
			return this;
		}

		public Builder value(Integer val) {
			value = val;
			return this;
		}

		public Builder status(String val) {
			status = val;
			return this;
		}

		public Builder remark(String val) {
			remark = val;
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

		public BAddressEthEntity build() {
			return new BAddressEthEntity(this);
		}
	}
}
