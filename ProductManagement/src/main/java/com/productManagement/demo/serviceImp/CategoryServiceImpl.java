package com.productManagement.demo.serviceImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.demo.entity.Category;
import com.productManagement.demo.repository.CategoryRepository;
import com.productManagement.demo.service.CategoryService;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository csRepo;

	@Override
	public boolean save(Category category) {
		try {
           csRepo.save(category);
           return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return csRepo.findById(id).get();
	}

	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return csRepo.save(category);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return csRepo.findByActiveTrue();
	}

	@Override
	public void deleteCategoryById(Long id) {
		csRepo.deleteById(id);
		
	}

}
