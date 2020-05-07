package com.feel.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告表
 *
 * @author feel
 * @email feel.com
 * @date 2019-08-21 02:24:03
 */
@Data
@TableName("f_ad")
public class FAdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 广告标题
	 */
	private String name;
	/**
	 * 所广告的商品页面或者活动页面链接地址
	 */
	private String link;
	/**
	 * 广告宣传图片
	 */
	private String url;
	/**
	 * 广告位置：1是首页轮播 2首页广告位 3商户广告 4公告
	 */
	private Integer position;
	/**
	 * 活动内容
	 */
	private String content;
	/**
	 * 广告开始时间
	 */
	private Date startTime;
	/**
	 * 广告结束时间
	 */
	private Date endTime;
	/**
	 * 类型:1banaer 2xxx
	 */
	private Integer type;
	/**
	 * 状态:1启用 2停用 3逻辑删除
	 */
	private Integer status;
	/**
	 * 创建时间

	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public FAdEntity() {}
}
