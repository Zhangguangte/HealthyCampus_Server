package com.muyou.common.vo;

import java.io.Serializable;

public class BookDetailVo implements Serializable{
	private String classify;
    private String name;
    private String author;
    private String price;
    private String publish;
    private String introduced;
    private String url;
    private int sum;
    private int rest;
    private String place;
    private String index;
    
//    public BookDetailVo(TbLibrary library) {
//    	book.classify = library.getClassify();
//		book.name = library.getName();
//		book.author= library.getAuthor();
//		book.price= library.getPrice();
//		book.publish= library.getPublish();
//		book.introduced= library.getIntroduced();
//		book.url= library.getUrl();
//		book.sum= library.getSum();
//		book.rest= library.getRest();
//		book.place= library.getPlace();
//		book.index= library.getIndex();
//	}
//    
    
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getRest() {
		return rest;
	}
	public void setRest(int rest) {
		this.rest = rest;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
    
}
