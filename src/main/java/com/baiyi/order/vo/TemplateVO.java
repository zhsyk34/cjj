package com.baiyi.order.vo;

import java.util.List;

import com.baiyi.order.model.Marquee;
import com.baiyi.order.model.Material;
import com.baiyi.order.util.EnumList.Effect;
import com.baiyi.order.util.EnumList.TemplateContentEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;

public class TemplateVO {

	private Integer id;

	private String name;

	private Material logo;

	private TemplateTypeEnum type;
	private int rowcount;
	private int colcount;

	private TemplateContentEnum content;

	private List<FoodVO> foodList;

	private Material number;

	private List<Material> videoList;

	private List<Material> pictureList;
	private int interlude;
	private Effect effect;

	private List<Marquee> marqueeList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Material getLogo() {
		return logo;
	}

	public void setLogo(Material logo) {
		this.logo = logo;
	}

	public TemplateTypeEnum getType() {
		return type;
	}

	public void setType(TemplateTypeEnum type) {
		this.type = type;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public int getColcount() {
		return colcount;
	}

	public void setColcount(int colcount) {
		this.colcount = colcount;
	}

	public TemplateContentEnum getContent() {
		return content;
	}

	public void setContent(TemplateContentEnum content) {
		this.content = content;
	}

	public List<FoodVO> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodVO> foodList) {
		this.foodList = foodList;
	}

	public Material getNumber() {
		return number;
	}

	public void setNumber(Material number) {
		this.number = number;
	}

	public List<Material> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Material> videoList) {
		this.videoList = videoList;
	}

	public List<Material> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Material> pictureList) {
		this.pictureList = pictureList;
	}

	public int getInterlude() {
		return interlude;
	}

	public void setInterlude(int interlude) {
		this.interlude = interlude;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public List<Marquee> getMarqueeList() {
		return marqueeList;
	}

	public void setMarqueeList(List<Marquee> marqueeList) {
		this.marqueeList = marqueeList;
	}

}
