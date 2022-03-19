package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;

//Thực hiện việc put dữ liệu người dùng vào session
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override //Load mật khẩu được chạy ngầm ở dưới
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		
		if(userEntity == null) {
			//Thất bại thì trả về authentication-failure-url trong security.xml
			throw new UsernameNotFoundException("User not found"); 
		}
		
		//Put thông tin người dùng
		
		//Lấy 1 list role
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleEntity role: userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		/*User user = new User(userEntity.getUserName(), userEntity.getPassword(),
				true, true, true, true, authorities);*/ 
		//Trường hợp trên chỉ có username + password, muốn có thêm phải tự tạo lớp kế thừa từ User
		MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(),
				true, true, true, true, authorities);
		myUser.setFullName(userEntity.getFullName());
		
		return myUser; //vì myUser = User = được impl từ UserDetails
	}

}
