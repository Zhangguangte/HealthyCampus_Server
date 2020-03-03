package com.muyou.front.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.muyou.common.form.RequestForm;
import com.muyou.common.redis.JedisClient;
import com.muyou.common.util.JsonUtils;
import com.muyou.front.service.LibraryService;
import com.muyou.front.vo.BookDetailVo;
import com.muyou.front.vo.BookVo;
import com.muyou.mapper.TbLibraryMapper;
import com.muyou.pojo.TbLibrary;
import com.muyou.pojo.TbLibraryExample;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${LIBRARY_DETAIL}")
	private String LIBRARY_DETAIL;

	@Value("${LIBRARY_SEARCH}")
	private String LIBRARY_SEARCH;
	
	@Value("${LIBRARY_EXPIRE}")
	private Integer LIBRARY_EXPIRE;

	@Autowired
	private TbLibraryMapper libraryMapper;

	@Override
	public BookDetailVo searchBookDetail(RequestForm requestForm) {

		try {
			String json = jedisClient.get(LIBRARY_DETAIL+":"+requestForm.getQuest_id());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToPojo(json, BookDetailVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLibrary library = libraryMapper.selectByPrimaryKey(Integer.parseInt(requestForm.getQuest_id()));
		if (null == library)
			throw null;
		BookDetailVo book = new BookDetailVo(library);

		try {
			jedisClient.set(LIBRARY_DETAIL+":"+requestForm.getQuest_id(), JsonUtils.objectToJson(book));
			jedisClient.expire(LIBRARY_DETAIL+":"+requestForm.getQuest_id(), LIBRARY_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public List<BookVo> searchBook(RequestForm requestForm) {
		try {
			String json = jedisClient.get(LIBRARY_SEARCH + ":" + requestForm.getContent());
			if (StringUtils.isNotBlank(json)) {
				return JsonUtils.jsonToList(json, BookVo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbLibraryExample tbLibraryExample = new TbLibraryExample();
		TbLibraryExample.Criteria criteria = tbLibraryExample.createCriteria();
		criteria.andNameLike(requestForm.getContent());
		List<TbLibrary> books = libraryMapper.selectByExample(tbLibraryExample);
		if (null == books || books.size() == 0)
			throw null;
		List<BookVo> bookVos = new LinkedList<>();
		for (TbLibrary tbLibrary : books) {
			bookVos.add(new BookVo(tbLibrary));
		}

		try {
			jedisClient.set(LIBRARY_SEARCH + ":" + requestForm.getContent(), JsonUtils.objectToJson(bookVos));
			jedisClient.expire(LIBRARY_SEARCH + ":" + requestForm.getContent(), LIBRARY_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookVos;
	}

}
