package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.NewEntity;

//Tương tự kế thừa từ GenericDAO là chứa các Interface của các câu lệnh select, insert... được viết sẵn
public interface NewRepository extends JpaRepository<NewEntity, Long> { //Class + type khóa chính

}
