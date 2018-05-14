package com.kanhaoyi.www.model;

/**
 * @discription 课程类型，描述现有的科室，比如内科，外科等...
 * @author zhuziming
 * @time 2018年4月17日下午4:03:33
 */
public class CourseType {

	private int id;
	private String name; // 课程类型 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
