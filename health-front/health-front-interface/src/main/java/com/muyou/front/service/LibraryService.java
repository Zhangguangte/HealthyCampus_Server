package com.muyou.front.service;

import java.util.List;

import com.muyou.common.form.RequestForm;
import com.muyou.front.pojo.BookDetailVo;
import com.muyou.front.pojo.BookVo;

/**
 * 图书馆服务
 * @author 木友茶
 *
 */
public interface LibraryService {

	 BookDetailVo searchBookDetail(RequestForm requestForm);

	 List<BookVo> searchBook(RequestForm requestForm);

}
