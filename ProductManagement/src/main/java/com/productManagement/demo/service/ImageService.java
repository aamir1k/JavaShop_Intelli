package com.productManagement.demo.service;

import java.util.List;

import com.productManagement.demo.entity.Images;

public interface ImageService {

	void save(Images img);

	//Images findById(Long id);

	//Optional<Images> findImageById(Long id);

	Images findById(Long id);

	Images getImageById(Long id);

	List<Images> getImagesForProduct(Long productId);


	

}
