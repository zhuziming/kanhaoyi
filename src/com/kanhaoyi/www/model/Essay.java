package com.kanhaoyi.www.model;

/**
 * @discription 短篇文章
 * @author zhuziming
 * @time 2018年4月28日下午3:03:40
 */
public class Essay {
	private Integer id;
	private String essay; // 短文
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEssay() {
		return essay;
	}
	public void setEssay(String essay) {
		this.essay = essay;
	}
}
