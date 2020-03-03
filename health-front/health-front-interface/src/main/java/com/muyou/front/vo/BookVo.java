package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbLibrary;

public class BookVo implements Serializable {
	private String id;
	private String bookName;
	private String author;
	private String publish;
	private int rest;
	private String classify;

	public BookVo() {}
	
	public BookVo(TbLibrary library) {
		this.id = library.getId() + "";
		this.bookName = library.getName();
		this.author = library.getAuthor();
		this.publish = library.getPublish();
		this.classify = library.getClassify();
		this.rest = library.getRest();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

}
