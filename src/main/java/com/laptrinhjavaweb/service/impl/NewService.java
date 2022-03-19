package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

//Tầng service
@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		List<NewDTO> models = new ArrayList<>();
		for(NewEntity entity: entities) {
			NewDTO model = newConverter.toDto(entity);
			models.add(model);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepository.findOne(id);
		return newConverter.toDto(entity);
	}

	/*@Override
	@Transactional //Tự quản lý commit, rollback cho thêm, sửa, xóa
	public NewDTO insert(NewDTO dto) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = newConverter.toEntity(dto);
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity); //Nếu ko có id thì insert, có thì sẽ update
		return newConverter.toDto(newEntity);
	}

	@Override
	@Transactional
	public NewDTO update(NewDTO dto) {
		NewEntity oldEntity = newRepository.findOne(dto.getId());
		oldEntity.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
		oldEntity.setTitle(dto.getTitle());
		oldEntity.setContent(dto.getContent());
		oldEntity.setShortDescription(dto.getShortDescription());
		return newConverter.toDto(newRepository.save(oldEntity));
	}*/
	
	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if (dto.getId() != null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(oldNew, dto);
		} else {
			newEntity = newConverter.toEntity(dto);
			newEntity.setCategory(category);
		}
		return newConverter.toDto(newRepository.save(newEntity));
	}

	@Override
	public void delete(long[] ids) {
		for(long id:ids) {
			newRepository.delete(id);
		}
	}
	
}
