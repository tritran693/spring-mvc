package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;

//API chỉ áp dụng cho thêm sửa xóa
//Cần add thêm thư viện để nhận biết json và restful web service
@RestController(value = "newAPIOfAdmin") //Xác định class có vai trò API, cần xác định trong dispatcher-serverlet để nó biết api ở đâu
public class NewAPI {

	@Autowired
	private INewService newService;
		
	@PostMapping("/api/new")
	public NewDTO createNew(@RequestBody NewDTO newDTO) { //newDTO là thứ được map dữ liệu vào từ client
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO updateNew(@RequestBody NewDTO newDTO) { //newDTO là thứ cần được map vào
		return newService.save(newDTO);
	}
	
	@DeleteMapping("/api/new")
	public void deleteNew(@RequestBody long[] ids) { //newDTO là thứ cần được map vào
		newService.delete(ids);
	}
}
