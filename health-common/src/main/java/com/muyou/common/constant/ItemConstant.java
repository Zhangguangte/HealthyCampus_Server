package com.muyou.common.constant;

public interface ItemConstant {

	/**
	 * 状态
	 */
	//启动
	public final static int OPEN = 1;
	
	//关闭
	public final static int CLOSE = 0;
	/**
	 * 项目
	 */
	// 讲座列表,根据学院名称
	public final static String LECTURE_LIST = "LECTURE_LIST";

	// 讲座详细,根据讲座ID
	public final static String LECTURE_DETAIL = "LECTURE_DETAIL";

	// 食谱,食堂三餐
	public final static String RECIPES_MEALS = "RECIPES_MEALS";

	// 课表,签到
	public final static String ATTEND = "ATTEND";

	/**
	 * 项目&目录
	 */
	// 关系,类型:科室:0
	public final static int RELA_DEP = 0;

	// 关系,类型:部位:1
	public final static int RELA_PAR = 1;

	// 关系,类型:药品:2
	public final static int RELA_MED = 2;

	// 关系,类型:食谱:3
	public final static int RELA_REC = 3;

	// 关系,类型:图书:4
	public final static int RELA_LIB = 4;

	// 关系,类型:讲座:5
	public final static int RELA_LEC = 5;

	// 关系,类型:课表:6
	public final static int RELA_TIM = 6;

	/**
	 * 目录
	 */
	// 目录,类型:疾病:0
	public final static int CATE_DISEASE = 0;

	// 目录,类型:药品:1
	public final static int CATE_MEDICINE = 1;

	// 目录,类型:食谱:2
	public final static int CATE_RECIPES = 2;

	// 目录,类型:食堂:3
	public final static int CATE_CATTEN = 3;

	// 目录,类型:讲座:5
	public final static int CATE_LECTURE = 5;

	// 目录,类型:班级:8
	public final static int CATE_CLASS = 8;

	// 目录:疾病,科室,数据库ID:1
	public final static int CATE_DEPARTMENT = 1;

	// 目录:疾病,部位,数据库ID:82
	public final static int CATE_PART = 82;

	// 目录:药品,父节点:0
	public final static int CATE_MEDICINE_PARENT = 0;

	// 目录:食谱,父节点:0
	public final static int CATE_RECIPES_PARENT = 0;

}
