package com.kanhaoyi.www.model;


/**
 * 
 * @discription 角色类
 * @author zhuziming
 * @time 2018年8月10日上午11:26:32
 */
public class Role {

	private Integer id;
	private String title; // 角色的汉字名称
	private String flag;  // 角色的英文名称
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
