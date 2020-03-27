package com.muyou.vo;

import java.io.Serializable;

import com.muyou.pojo.TbMedicine;

public class MedicineListVo implements Serializable{
	
	public String id;
	public String price;
	public String goodName;
	public String description;
	public String isOct; // 非处方药
	public String image;

	public MedicineListVo() {}
	
	public MedicineListVo(TbMedicine medicine) {
		this.id = medicine.getId()+"";
		this.price = medicine.getPrice()+"";
		this.goodName = medicine.getGoodsName();
		this.description = medicine.getManufacturer();
		//1.代表处方药;0.代表非处方药
		this.isOct = "1".equals(medicine.getOtc())?"处方药":"非处方药";
		this.image = medicine.getLogo();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsOct() {
		return isOct;
	}

	public void setIsOct(String isOct) {
		this.isOct = isOct;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



}
