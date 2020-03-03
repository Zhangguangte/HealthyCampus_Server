package com.muyou.front.vo;

import java.io.Serializable;

import com.muyou.pojo.TbMedicine;

public class MedicineDetailVo implements Serializable {

	private String id;
	private String image;
	private String detail;
	private String name;
	private String price;
	private String spec;
	private String unit;
	private String zhuzhi;
	private boolean collect;

	public MedicineDetailVo() {}
	
	public MedicineDetailVo(TbMedicine medicine) {
		this.id = medicine.getId()+"";
		this.price = medicine.getPrice()+"";
		this.detail = medicine.getInstruction();
		this.name = medicine.getGoodsName();
		this.spec = medicine.getSpec();
		this.unit = medicine.getUnit();
		this.zhuzhi = medicine.getIndications();
		this.image = medicine.getLogo();
	}
	
	public String getId() {
		return id;
	}
	public void setCollect(boolean collect) {
		this.collect = collect;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getZhuzhi() {
		return zhuzhi;
	}

	public void setZhuzhi(String zhuzhi) {
		this.zhuzhi = zhuzhi;
	}

	public boolean isCollect() {
		return collect;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
