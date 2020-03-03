package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.front.vo.LectureVo;

/**
 * 讲座服务
 * @author 木友茶
 *
 */
public interface LectureService {

	 LectureVo getLectureDetail(RequestForm requestForm) ;

	 List<LectureVo> getLectureList(RequestForm requestForm);
	
}
