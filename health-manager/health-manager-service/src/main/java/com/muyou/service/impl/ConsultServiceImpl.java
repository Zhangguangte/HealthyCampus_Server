package com.muyou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.ConsultPictureForm;
import com.muyou.common.form.PatienInforBean;
import com.muyou.mapper.TbConsultMapper;
import com.muyou.mapper.TbConsultUserMapper;
import com.muyou.pojo.TbConsult;
import com.muyou.service.ConsultService;

@Service("consultServiceImpl")
public class ConsultServiceImpl implements ConsultService {
	@Autowired
	private TbConsultMapper consultMapper;

	@Autowired
	private TbConsultUserMapper consultUserMapper;

	@Override
	public void saveConsultPicture(ConsultPictureForm form, String userid) {
		System.out.println("*************");
		System.out.println("form" + form.toString());
		
		// 基本信息
		TbConsult consult = new TbConsult();
		consult.setDescr(form.getDescribe());
		consult.setPrescription(form.isPrescription() ? "YES" : "NO");
		consult.setHistory(form.isHistory() ? "YES" : "NO");
		consult.setImages(form.getImages());
		consult.setUserId(userid);
		consultMapper.insertSelective(consult);
		//自增之後，返回id为插入后的调用id，consult.getId()
		if (null != form.getPatienInforBeans() && form.getPatienInforBeans().size() != 0) {// 患者基本信息
			for (PatienInforBean bean : form.getPatienInforBeans()) {
				consultUserMapper.insertConsultUser(consult.getId(), bean);
			}
		}
		System.out.println("***********************");
	}

}
