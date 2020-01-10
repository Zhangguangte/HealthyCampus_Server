package com.muyou.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.vo.DiseaseDetailVo;
import com.muyou.common.vo.DiseaseSortListVo;
import com.muyou.common.vo.DiseaseSortVo;
import com.muyou.mapper.TbDepartmentMapper;
import com.muyou.mapper.TbDiseaseMapper;
import com.muyou.mapper.TbPartMapper;
import com.muyou.pojo.TbDepartment;
import com.muyou.pojo.TbDisease;
import com.muyou.pojo.TbDiseaseExample;
import com.muyou.pojo.TbPart;
import com.muyou.service.DiseaseService;

@Service("diseaseServiceImpl")
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private TbDiseaseMapper diseaseMapper;

	@Autowired
	private TbPartMapper partMapper;

	@Autowired
	private TbDepartmentMapper departmentMapper;

	// 疾病分类
	@Override
	public List<DiseaseSortListVo> getDiseaseSortList(RequestForm requestForm) {

		List<DiseaseSortListVo> list = new LinkedList<>();
		if ("0".equals(requestForm.getQuest_id())) { // 部位
			List<TbPart> parts = partMapper.selectAll();
			DiseaseSortListVo diseaseSortListVo;
			if (null == parts)
				return null;
			for (TbPart part : parts) {
				diseaseSortListVo = new DiseaseSortListVo();
				diseaseSortListVo.setTitle(part.getPartName());
				diseaseSortListVo.setSubName(Arrays.asList(part.getRelatedOrgans().split(",")));
				list.add(diseaseSortListVo);
			}
		} else { // 科室
			List<TbDepartment> departments = departmentMapper.selectAll();
			DiseaseSortListVo diseaseSortListVo;
			if (null == departments)
				return null;
			for (TbDepartment department : departments) {
				diseaseSortListVo = new DiseaseSortListVo();
				diseaseSortListVo.setTitle(department.getDepartName());
				diseaseSortListVo.setSubName(Arrays.asList(department.getSubName().split(",")));
				list.add(diseaseSortListVo);
			}
		}
		return list;
	}

	// 分类疾病
	@Override
	public List<DiseaseSortVo> getDiseaseSort(RequestForm requestForm) {
		TbDiseaseExample diseaseExample = new TbDiseaseExample();
		diseaseExample.setOrderByClause("id");
		diseaseExample.setRow(requestForm.getRow());
		diseaseExample.setSize(15);
		TbDiseaseExample.Criteria criteria = diseaseExample.createCriteria();
		if ("0".equals(requestForm.getQuest_id())) { // 部位
			criteria.andDiseasePartLike(requestForm.getContent());
		} else if ("1".equals(requestForm.getQuest_id())) { // 科室
			criteria.andCureDepartLike(requestForm.getContent());
		} else { // 关键字
			criteria.andDiseaseNameLike(requestForm.getContent());
		}
		List<TbDisease> diseases = diseaseMapper.selectByExample(diseaseExample);
		if (null == diseases || diseases.size() ==0)
			return null;
		List<DiseaseSortVo> result = new LinkedList<DiseaseSortVo>();
		DiseaseSortVo diseaseSortVo;
		for (TbDisease disease : diseases) {
			diseaseSortVo = new DiseaseSortVo();
			diseaseSortVo.setId(disease.getId() + "");
			diseaseSortVo.setTitle(disease.getDiseaseName());
			diseaseSortVo.setIntroduction(disease.getDiseaseIntroduce());
			diseaseSortVo.setUrl(disease.getDiseaseUrl());
			result.add(diseaseSortVo);
		}
		return result;
	}

	// 疾病详细
	@Override
	public DiseaseDetailVo getDiseaseDetail(RequestForm requestForm) {
		TbDisease disease = null;
		if ("ID".equals(requestForm.getQuest_id())) {
			disease = diseaseMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getContent()));
			if (null == disease)
				return null;
		} else if ("NAME".equals(requestForm.getQuest_id())) {
			List<TbDisease> diseases = diseaseMapper.getDiseaseDetailByName(requestForm.getContent());
			if (null == diseases || diseases.size() == 0)
				return null;
			else
				disease = diseases.get(0);
		}
		DiseaseDetailVo detailVo = new DiseaseDetailVo();
		detailVo.setDiseaseName(disease.getDiseaseName());
		detailVo.setSymbol(disease.getDiseaseSymbol());
		detailVo.setAlias(disease.getDiseaseAlias());
		detailVo.setIntroduce(disease.getDiseaseIntroduce());
		detailVo.setContagious(disease.getDiseaseContagious());
		detailVo.setCureRate(disease.getCureRate());
		detailVo.setCureDepart(disease.getCureDepart());
		detailVo.setCost(disease.getCureCost());
		detailVo.setCureWay(disease.getCureWay());
		detailVo.setRecommendDrug(disease.getCureRecommendDrug());
		detailVo.setPart(disease.getDiseasePart());
		detailVo.setInsurance(disease.getMedicalInsurance());
		detailVo.setComplication(disease.getDiseaseComplication());
		detailVo.setCureTime(disease.getCureTime());
		detailVo.setPopulation(disease.getDiseasePopulation());
		detailVo.setTypicalSymptoms(disease.getDiseaseTypicalSymptoms());
		detailVo.setPrevention(disease.getDiseasePrevention());
		detailVo.setDiseaseCase(disease.getDiseaseCase());
		detailVo.setCheck(disease.getDiseaseCheck());
		detailVo.setUrl(disease.getDiseaseUrl());
		return detailVo;
	}

}
