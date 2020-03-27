package com.muyou.common.constant;

public interface RedisConstant {

	// 权限,所有权限列表
	public final static String SHIRO_LIST = "SHIRO_LIST";

	/**
	 * 分类
	 */
	
	// 疾病分类，根据科室、部位
	public final static String DISEASE_CLASSIFY = "DISEASE_CLASSIFY";

	// 药品分类，获得所有分类
	public final static String MEDICINE_CLASSIFY = "MEDICINE_CLASSIFY";

	// 食谱分类，根据所有分类
	public final static String RECIPES_CLASSIFY = "RECIPES_CLASSIFY";
		
	// 功能食谱,根据食谱分类ID
	public final static String RECIPES_FUNCTION = "RECIPES_FUNCTION";

	// 食谱列表,根据食材分类名称
	public final static String RECIPES_LIST = "RECIPES_LIST";

	// 课表列表,根据班级名称
	public final static String TIMETABLE_LIST = "TIMETABLE_LIST";

	// 讲座列表,根据学院名称
	public final static String LECTURE_LIST = "LECTURE_LIST";

	// 讲座详细过期时间,过期时间30分钟
	public final static Integer LECTURE_EXPIRE = 1800;

	/**
	 * solr
	 */
	
	// solr,查询,疾病
	public final static String SOLR_SEARCH_DIS = "SOLR_SEARCH_DIS";
	
	// solr,查询,药品
	public final static String SOLR_SEARCH_MED = "SOLR_SEARCH_MED";
	
	// solr,查询,过期时间30分钟
	public final static Integer SOLR_EXPIRE = 1800;
}
