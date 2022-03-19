package com.laptrinhjavaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//Định nghĩa BaseEntity là parent class
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //Hỗ trợ cho các thuộc tính @CreatedDate,...
public abstract class BaseEntity {

	@Id // Xác định là khóa chính + not null
	// Còn những thuộc tính khác có thể tự custom như auto increment, not null,
	// text...
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;

	@Column(name = "createdDate")
	@CreatedDate //Auto set createdDate, dùng cơ chế Spring Data JPA auditing, phải thêm thư viện trong pom
	private Date createdDate;

	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifiredDate;

	@Column(name = "modifiedby")
	@LastModifiedBy
	private String modifiedBy;

	@Column(name = "createdby")
	@CreatedBy
	private String createdBy;

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getModifiredDate() {
		return modifiredDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Long getId() {
		return id;
	}

}
