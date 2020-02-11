package com.muyou.sso.service;

import com.muyou.sso.form.ConsultPictureForm;

/**
 * 咨询服务
 * @author 木友茶
 *
 */
public interface ConsultService {

	// 图文咨询
	 void saveConsultPicture(ConsultPictureForm form, String userid);

}
