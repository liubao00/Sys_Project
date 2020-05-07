package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * app版本管理
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-30 20:48:09
 */
@Data
@TableName("b_app_version")
public class BAppVersionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * URL地址
	 */
	private String url;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * type: 1 ios/ 2apk
	 */
	private Integer type;
	/**
	 * 状态：0无效，1生效
	 */
	private Integer status;
	/**
	 * 更新说明
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

	public BAppVersionEntity() {}

	private BAppVersionEntity(Builder builder) {
		this.id = builder.id;
		this.url = builder.url;
		this.version = builder.version;
		this.type = builder.type;
		this.status = builder.status;
		this.remark = builder.remark;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static Builder newBAppVersionEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private String url;
		private String version;
		private Integer type;
		private Integer status;
		private String remark;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public BAppVersionEntity build() {
			return new BAppVersionEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder type(Integer type) {
			this.type = type;
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
