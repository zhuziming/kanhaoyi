package com.kanhaoyi.www.model;


/**
 * @description 课程部位关联表
 * @author zhuziming
 * @time 2018年7月29日 下午5:22:27
 */
public class CoursePeople {

	private Integer id;
	private Integer courseID; // 课程id
	private Integer PeoplePartID; // 部位id
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public Integer getPeoplePartID() {
		return PeoplePartID;
	}
	public void setPeoplePartID(Integer peoplePartID) {
		PeoplePartID = peoplePartID;
	}
}
