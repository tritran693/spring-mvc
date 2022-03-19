package com.laptrinhjavaweb.controller.admin;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.util.MessageUtil;


@Controller(value = "newControllerOfAdmin")
public class NewController {
	
	/*Anotation @Autowired sẽ tìm các beans như @Serivce và @Repository 
	trong package được khai báo trong Context/component-scan
	để inject đối tượng được implement từ INewService
	https://stackoverflow.com/questions/19414734/understanding-spring-autowired-usage
	*/
	@Autowired 
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	//@ModelAttribute sẽ ánh xạ các giá trị từ atb tên "model" vào trong model
	public ModelAndView showList(@RequestParam("page") int page, 
									@RequestParam("limit") int limit,
									HttpServletRequest req) {
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/new/list");
		Pageable pageble = new PageRequest(page -1 , limit); //Spring cung cấp sẵn pageble, page-1 là offset(vị trí bắt đầu để cộng thêm limit)
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int)Math.ceil((double)model.getTotalItem()/model.getLimit()));
		mav.addObject("model", model);
		if(req.getParameter("message")!=null) {
			Map<String, String> message =  messageUtil.getMessage(req.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		return mav;  //Trả ModelAndView về cho Dispatcher-servlet
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, 
									HttpServletRequest req) { //Có hoặc ko có biến
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if(id != null) {
			model = newService.findById(id);
		}
		if(req.getParameter("message")!=null) {
			Map<String, String> message =  messageUtil.getMessage(req.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
}
