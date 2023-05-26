package com.productManagement.demo.controllers;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.productManagement.demo.entity.Images;
import com.productManagement.demo.entity.Product;
import com.productManagement.demo.repository.ImagesRepository;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.service.ImageService;
import com.productManagement.demo.service.ProductService;

import utils.Constants;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImagesRepository imageRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ProductService prodService;

    @Autowired
    ProductRepository prodRepo;

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id) {
        Images image = imageService.findById(id);
        try {
            Path path = Paths.get(Constants.PATH + image.getName());
            byte[] data = Files.readAllBytes(path);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getType())).body(data);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("updateimage")
    public ResponseEntity<?> updateImage(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam("productId") Long productId, @RequestParam("id") Long id,
                                         @RequestParam("file") MultipartFile file) {

        Product product = prodService.getProductById(productId);
        Images image = imageService.getImageById(id);
        if (product == null || image == null) {
            return ResponseEntity.badRequest().body("Product or Image not found");
        }

        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setProduct(product);

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(Constants.PATH + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Images updatedImage = imageRepository.save(image);
        return ResponseEntity.status(HttpStatus.OK).body(updatedImage);
    }


    @GetMapping("/{id}/image")
    public ResponseEntity<List<byte[]>> getProductImages(@PathVariable Long id) {
        Optional<Product> optionalProduct = prodRepo.findById(id);
        if (optionalProduct.isPresent()) {
            List<Images> images = optionalProduct.get().getImages();
            List<byte[]> imageBytes = new ArrayList<>();
            for (Images image : images) {
                imageBytes.add(image.getImage());

            }
            return ResponseEntity.ok().body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
