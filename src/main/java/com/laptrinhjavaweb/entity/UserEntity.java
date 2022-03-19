package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity //Khi này, class được biến thành Entity rồi ánh xạ dữ liệu qua DBMS
@Table(name = "user")  //Khi chạy, nó sẽ scan Entity này và tạo 1 bảng tương ứng trong DBMS
public class UserEntity extends BaseEntity{
	
	//Xác định một Column trong bảng + xác định name
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "status")
	private Integer status; 
	
	//Một User có nhiều role
	@ManyToMany(fetch = FetchType.LAZY) //Default là Lazy: là load role lên sau user (phải config thêm trong JPAConfig), //EAGER là load lên 1 lúc 
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), //Tên id của bảng này trong bảng trung gian
								  inverseJoinColumns = @JoinColumn(name = "roleid")) //Tên id còn lại trong bảng trung gian
	private List<RoleEntity> roles = new ArrayList<>(); //Bên Role phải map đến biến roles này
	
	//Em biết không, cả vũ trụ đang dừng lại để chúc mừng sinh nhật em đó
	//Chúc em - một nửa thế giới của anh sinh nhật vui vẻ, hạnh phúc.
	//Chúc em tuổi mới ăn mau chóng lớn, ngủ sớm và đủ giấc hơn nhé.
	//Mặc dù không thể cùng em đón sinh nhật vào ngày mai nhưng sẽ hẹn tổ chức sinh nhật em vào những ngày sau nhé.
	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		
}
