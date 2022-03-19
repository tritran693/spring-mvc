package com.laptrinhjavaweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// Anotation này để biến class HomeController thành một Controller của mô hình MVC 
//Value để phân biệt các Controller có cùng tên 
@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	@RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
	//Model là những dữ liệu sẽ được đẩy lên View, mà view ở đây là home.jsp
	// đường dẫn đến home.jsp đã được định nghĩa trong dispacher-servlet
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;  //Trả ModelAndView về cho Dispatcher-servlet
	}
}
