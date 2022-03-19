package com.laptrinhjavaweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// Anotation này để biến class HomeController thành một Controller của mô hình MVC 
//Value để phân biệt các Controller có cùng tên 
@Controller(value = "homeControllerOfWeb")
public class HomeController {
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	//Model là những dữ liệu sẽ được đẩy lên View, mà view ở đây là home.jsp
	// đường dẫn đến home.jsp đã được định nghĩa trong dispacher-servlet
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;  //Trả ModelAndView về cho Dispatcher-servlet
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // = isAuthenticated() jsp
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
}
