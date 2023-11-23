package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Category;
import com.example.demo.model.Provider;
import com.example.demo.repository.CategoryRepo;

@Service
public class CategoryServ {

	@Autowired
	CategoryRepo categoryRepo;

	public List<Category> getList() {
		return categoryRepo.findAll();
	}

	public Optional<Category> getOne(Long id) {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isPresent()) {
			Category categoryBody = categoryRepo.findById(id).get();
			System.out.println(categoryBody);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not-found-with-id," + id);
		}
		return category;
	}

	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}

	public Category updateCategory(Long id, Category category) {
		Category categoryId = categoryRepo.findById(id)
				.orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
		Category categoryBody = categoryRepo.findById(id).get();
		
		System.out.println(categoryBody.getCode());
		if (categoryRepo.existsByCode(category.getCode()) && !categoryBody.getCode().equals(category.getCode())) {
			throw new GPSException(ExceptionUtils.E_RECORD_EXIST);
		}
		categoryBody.setCode(category.getCode());
		categoryBody.setName(category.getName());
		return categoryRepo.save(categoryBody);
	}

	public Boolean deleteCategory(Long id) {
		categoryRepo.deleteById(id);
		return true;
	}

	public List<String> findByName(String name) {
		if (categoryRepo.findWithName(name).size() <= 0)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not-found-with-name," + name);
		return categoryRepo.findWithName(name);
	}
}
