package com.productManagement.demo.serviceImp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.ProductImages;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {
	
	 private final Path root = Paths.get("uploads");

	@Autowired
	private ProductRepository productRepo;

	@Override
	public boolean save(Product product) {
		try {
			productRepo.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).get();
	}

	@Override
	public Product saveProduct(Product product) {
		Product result = productRepo.save(product);
		return result;
	}

	/*
	 * @Override public Product deleteProductById(Long id) {
	 * productRepo.deleteById(id);
	 * 
	 * }
	 */


	@Override
	public List<Product> findAll() {
		
		return productRepo.findAll();
	}


	@Override
	public List<ProductImages> findByProductId(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findByProductId(id);
	}



	@Override
	public void init() {
		 try {
		      Files.createDirectory(root);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
	}




	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
		
	}




	@Override
	public Product findById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).get();
	}


	@Override
	public Product findByIdWithImages(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findByIdWithImages(id);
	}


	@Override
	public void deleteProductById(Long id) {
		productRepo.deleteById(id);
	}










	

}
