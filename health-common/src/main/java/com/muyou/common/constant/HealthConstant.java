package com.muyou.common.constant;

public interface HealthConstant {

	/**
	 * 管理员
	 */

	// 管理员登录令牌Key
	public final static String TOKEN_KEY = "HEALTH_TOKEN";

	// 管理员登录
	public final static String ASESSION = "ASESSION";

	// 管理员登录过期时间
	public final static Integer ASESSION_EXPIRE = 1800;

	/**
	 * 管理员角色
	 */

	// 角色,类型:教师:15
	public final static Integer ROLE_TEA = 15;

	/**
	 * 系统
	 */

	// 界面浏览,界面浏览数
	public final static String PAGE_BROWSE = "PAGE_BROWSE";

	/**
	 * 日志
	 */

	// 日志,日志个数
	public final static String LOG_COUNT = "LOG:COUNT";

	/**
	 * 轮播图
	 */

	// 轮播图数据
	public final static String BANNER = "BANNER";

	// 板块,类型:轮播图:1
	public final static Integer BANNERID = 1;

	/**
	 * 文章地址
	 */

	// 跳转文章链接
	public final static String HTML_PATH = "http://192.168.2.108/article/";

	/**
	 * 图片上传地址
	 */

	public final static String IMAGE_SERVER_URL = "http://192.168.2.134/";
}
