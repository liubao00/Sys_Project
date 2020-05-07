package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * AB区奖金统计表
 *
 * @author feel
 * @email feel.com
 * @date 2019-09-09 09:48:32
 */
@Data
@TableName("f_user_team_score")
public class FUserTeamScoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增
	 */
	@TableId
	@ApiModelProperty(name = "id" , value = "自增")
private Integer id;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(name = "userId" , value = "用户ID")
private Integer userId;
	/**
	 * 用户区间 0未分配 1A区间  2B区间
	 */
	@ApiModelProperty(name = "section" , value = "用户区间 0未分配 1A区间  2B区间")
private Integer section;
	/**
	 * 累计奖金
	 */
	@ApiModelProperty(name = "grandTotal" , value = "累计奖金")
private BigDecimal grandTotal;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark" , value = "备注")
private String remark;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createTime" , value = "创建时间")
private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(name = "updateTime" , value = "修改时间")
private Date updateTime;

	public FUserTeamScoreEntity() {}

	private FUserTeamScoreEntity(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.section = builder.section;
		this.grandTotal = builder.grandTotal;
		this.remark = builder.remark;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static Builder newFUserTeamScoreEntity() {
		return new Builder();
	}


	public static final class Builder {
		private Integer id;
		private Integer userId;
		private Integer section;
		private BigDecimal grandTotal;
		private String remark;
		private Date createTime;
		private Date updateTime;

		public Builder() {
		}

		public FUserTeamScoreEntity build() {
			return new FUserTeamScoreEntity(this);
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder userId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder section(Integer section) {
			this.section = section;
			return this;
		}

		public Builder grandTotal(BigDecimal grandTotal) {
			this.grandTotal = grandTotal;
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
