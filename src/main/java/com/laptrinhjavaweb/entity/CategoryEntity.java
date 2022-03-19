package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // Khi này, class được biến thành Entity rồi ánh xạ dữ liệu qua DBMS
@Table(name = "category") // Khi chạy, nó sẽ scan Entity này và tạo 1 bảng tương ứng trong DBMS
public class CategoryEntity extends BaseEntity {

	// Xác định một Column trong bảng + xác định name
	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@OneToMany(mappedBy = "category") //Map đến biến category nằm trong của NewEntity
	private List<NewEntity> news = new ArrayList<>();

	public List<NewEntity> getNews() {
		return news;
	}

	public void setNews(List<NewEntity> news) {
		this.news = news;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
