package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.front.vo.BookDetailVo;
import com.muyou.front.vo.BookVo;

/**
 * 图书馆服务
 * @author 木友茶
 *
 */
public interface LibraryService {

	 BookDetailVo searchBookDetail(RequestForm requestForm);

	 List<BookVo> searchBook(RequestForm requestForm);

}
