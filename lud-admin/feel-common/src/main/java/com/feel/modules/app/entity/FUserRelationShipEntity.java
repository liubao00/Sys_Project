package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 前端用戶层级关系表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-24 00:14:56
 */
@Data
@TableName("f_user_relation_ship")
public class FUserRelationShipEntity implements Serializable {
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
	 * 用户属于哪个区间
	 */
	private Integer section;
	/**
	 * 父级id
	 */
	private Integer parentId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 用户层级路径
	 */
	private String idKey;

	public FUserRelationShipEntity() {}

	private FUserRelationShipEntity(Builder builder) {
		setId(builder.id);
		setUserId(builder.userId);
		setSection(builder.section);
		setParentId(builder.parentId);
		setCreateTime(builder.createTime);
		setUpdateTime(builder.updateTime);
		setIdKey(builder.idKey);
	}


	public static final class Builder {
		private Integer id;
		private Integer userId;
		private Integer section;
		private Integer parentId;
		private Date createTime;
		private Date updateTime;
		private String idKey;

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

		public Builder section(Integer val) {
			section = val;
			return this;
		}

		public Builder parentId(Integer val) {
			parentId = val;
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

		public Builder idKey(String val) {
			idKey = val;
			return this;
		}

		public FUserRelationShipEntity build() {
			return new FUserRelationShipEntity(this);
		}
	}
}
