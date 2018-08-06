package com.kanhaoyi.www.model;


/**
 * @description 人体部位
 * @author zhuziming
 * @time 2018年7月29日 下午5:20:37
 */
public class PeoplePart {

	private Integer id;
	private String partName; // 部位名字
	private String nameSpace; // 命名空间
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	
}
