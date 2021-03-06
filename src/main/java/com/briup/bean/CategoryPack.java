package com.briup.bean;


import javax.persistence.Column;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
@Api
public class CategoryPack {
	@ApiModelProperty(value="栏目id")
	private Integer id;
	@ApiModelProperty(value="栏目代码")
	private long code;
	@ApiModelProperty(value="栏目名")
	@Column(unique=true)
	private String name;
	
	
	public CategoryPack(Integer id, long code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		
	}
	public CategoryPack() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CategoryPack [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	
}
