package com.muyou.sso.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.sso.pojo.NoticeVo;

/**
 * 通知服务
 * @author 木友茶
 *
 */
public interface NoticeService {

	// 获得所有通知
		public List<NoticeVo> getAllNotice(RequestForm requestForm, String userId);

		// 清空通知
		public void clearNotice(String userId);

		// 删除通知
		public void deleteNotice(RequestForm requestForm, String userId);

		
		// 查看通知
		public void lookNotice(RequestForm requestForm, String userId);
	
}
