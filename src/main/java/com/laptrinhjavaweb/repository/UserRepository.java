package com.laptrinhjavaweb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.UserEntity;

//Tương tự kế thừa từ GenericDAO là chứa các Interface của các câu lệnh select, insert...
public interface UserRepository extends JpaRepository<UserEntity, Long> { //Class + type khóa chính
	
	//Chú ý quy tắc đặt tên, tất cả chữ đầu tiên đều viết hoa
	UserEntity findOneByUserNameAndStatus(String name, int status);
}
