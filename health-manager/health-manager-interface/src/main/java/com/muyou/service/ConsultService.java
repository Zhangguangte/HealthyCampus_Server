package com.muyou.service;

import com.muyou.common.form.ConsultPictureForm;

public interface ConsultService {

	// 图文咨询
	public void saveConsultPicture(ConsultPictureForm form, String userid);

}
