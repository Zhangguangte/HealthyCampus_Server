package com.muyou.search.service;

import java.util.List;

import com.muyou.vo.DiseaseSortVo;
import com.muyou.vo.MedicineListVo;

public interface SearchResultService {

	List<MedicineListVo> searchMedicine(String keyword, String field, int page, int rows) throws Exception;

	List<DiseaseSortVo> searchDisease(String keyword, String field, int page, int rows) throws Exception;

}
