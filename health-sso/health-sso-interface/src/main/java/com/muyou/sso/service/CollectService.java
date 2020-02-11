package com.muyou.sso.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.sso.pojo.CollectVo;

/**
 * 收藏服务
 * @author 木友茶
 *
 */
public interface CollectService {

	// 存储收藏
	public void saveCollect(RequestForm form, String userid);

	// 删除收藏
	public void deleteCollect(RequestForm form,String userid);

	// 是否收藏
	public boolean isCollect(String type, String tid, String userid);

	// 所有收藏
	public List<CollectVo> getAllCollect(String userid);
	
}
