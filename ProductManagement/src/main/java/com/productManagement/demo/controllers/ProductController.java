package com.productManagement.demo.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.productManagement.demo.entity.Rating;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.repository.RatingRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.productManagement.demo.entity.Images;
import com.productManagement.demo.entity.Product;
import com.productManagement.demo.entity.ProductVariant;
import com.productManagement.demo.repository.ProductVariantRepository;
import com.productManagement.demo.service.ImageService;
import com.productManagement.demo.service.ProductService;
import org.springframework.jdbc.core.JdbcTemplate;

import utils.Constants;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @Autowired
    ProductService prodService;
    //private Object productImage;
    @Autowired
    private ImageService imageService;

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    @Autowired
    private RatingRepository ratingRepository;
      @Autowired
      private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository pvr;


    @PostMapping(value = "/updateProduct", consumes = {"multipart/form-data",
            MediaType.MULTIPART_FORM_DATA_VALUE}) /*
     * headers = "content-type=multipart/*", consumes = {
     * MediaType.APPLICATION_JSON_VALUE,
     * MediaType.MULTIPART_FORM_DATA_VALUE, "application/*" })
     */
    public ResponseEntity<?> updateProduct(HttpServletRequest request, HttpServletResponse response,
                                           @RequestPart Product product, @RequestPart(value = "files", required = false) MultipartFile[] files) {
        System.err.println(product.getProduct() + "bbbbbbbbbbbbbb");
        for (MultipartFile multipartFile : files) {
            System.err.println("hehehehheh" + multipartFile.getContentType());
            System.err.println("nameeee" + multipartFile.getOriginalFilename());
        }

        if (product != null && product.getId() != null) {
            Product productEntity = prodService.getProductById(product.getId());
            product.setId(productEntity.getId());
            productEntity.setDescription(product.getDescription());
            productEntity.setComments(product.getComments());
            productEntity.setDetails(product.getDetails());
            productEntity.setPrice(product.getPrice());
            productEntity.setRating(product.getRating());
            productEntity.setReviews(product.getReviews());
            productEntity.setStatus(product.getStatus());
            productEntity.setColor(product.getColor());
            productEntity.setCount(product.getCount());
            productEntity.setProductName(product.getProductName());
            Product result = prodService.saveProduct(productEntity);

            // Check if there are any files (images) to update

            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    Images img = new Images();
                    img.setName(file.getOriginalFilename());

                    img.setType(file.getContentType());
                    img.setProduct(result);
                    try {
                        byte[] bytes = file.getBytes();
                        Path path = Paths.get(Constants.PATH + file.getOriginalFilename());
                        Files.write(path, bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageService.save(img);
                }

            }
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else if (product != null && product.getId() == null) {
            Product productEntity = new Product();
            productEntity.setDescription(product.getDescription());
            productEntity.setComments(product.getComments());
            productEntity.setDetails(product.getDetails());
            productEntity.setPrice(product.getPrice());
            productEntity.setRating(product.getRating());
            productEntity.setReviews(product.getReviews());
            productEntity.setStatus(product.getStatus());
            productEntity.setProductName(product.getProductName());
            productEntity.setColor(product.getColor());
            productEntity.setCount(product.getCount());
            productEntity.setCreatedAt(new Date());
            Product newProduct = prodService.saveProduct(productEntity);
            System.err.println(product.getDescription() + "aaaaaaaaaaaaaaaaaaaaa");
            for (MultipartFile file : files) {

                Images img = new Images();
                img.setName(file.getOriginalFilename());
                System.out.println(file.getOriginalFilename() + "jjjjjjjjjjjjjjjj");
                img.setType(file.getContentType());
                try {
                    img.setImage(file.getBytes());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                img.setProduct(newProduct);

                /*
                 * try { byte[] bytes = file.getBytes(); Path path = Paths.get(Constants.PATH +
                 * file.getOriginalFilename()); Files.write(path, bytes); } catch (IOException
                 * e) { e.printStackTrace(); }
                 */

                imageService.save(img);
            }

            List<ProductVariant> variants = product.getVariants();
            if (variants != null) {
                List<ProductVariant> productVariants = variants.stream().map(variant -> {
                    ProductVariant productVariant = new ProductVariant();
                    productVariant.setSize(variant.getSize());
                    productVariant.setQuantity(variant.getQuantity());
                    productVariant.setWeight(variant.getWeight());
                    productVariant.setProduct(newProduct);
                    return productVariant;
                }).collect(Collectors.toList());
                pvr.saveAll(productVariants);
            }

            return ResponseEntity.status(HttpStatus.OK).body(newProduct);

        } else {
            return ResponseEntity.badRequest().body("Product Details Are missing");
        }
    }

    @GetMapping("/getImage/{imageName:.+}")
    public void getImage(@PathVariable("imageName") String name, Images images, HttpServletRequest req,
                         HttpServletResponse rep) throws IOException {

        try {
            InputStream is = new FileInputStream(Constants.PATH + name);
            byte[] bytes = IOUtils.toByteArray(is);
            rep.setContentType(getContentType(name));
            OutputStream os = rep.getOutputStream();
            os.write(bytes);
            os.close();
            is.close();
        } catch (Exception e) {
        }

    }

    public static String getContentType(String imageName) {
        if (imageName.contains(".pdf"))
            return "application/pdf";
        else if (imageName.contains(".dwg"))
            return "imsage/vnd.dwg";
        else if (imageName.contains(".gif"))
            return "image/gif";
        else
            return "image/jpeg";
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> getAll() {
        List<Product> product = prodService.findAll();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findProduct/{id}")
    public ResponseEntity<?> findProduct(@PathVariable("id") Long id, HttpServletRequest request,
                                         HttpServletResponse response) {
        Product prod = prodService.findByIdWithImages(id);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<List<Product>> deleteProductItem(@PathVariable("id") Long id, HttpServletRequest request,
                                                           HttpServletResponse response) {
        prodService.deleteProductById(id);
        return ResponseEntity.ok().build();

    }



    /*--------------------------------------------------------------------------*/

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam Map<String, String> params) {
        try {
            // Filtering
            Map<String, String> queryObj = new HashMap<>(params);
            String[] excludeFields = {"page", "sort", "limit", "fields", "price","color"};
            Arrays.stream(excludeFields).forEach(el -> queryObj.remove(el));
            String queryStr = "";
            if (params.containsKey("price")) {
                String priceParam = params.get("price");
                System.out.println("priceeeeee------------"+priceParam);
                String[] priceValues = priceParam.split(",");
                for (int i = 0; i < priceValues.length; i++) {
                    String[] priceArr = priceValues[i].split(":");
                    if (priceArr.length == 2) {
                        String operator = priceArr[0];
                        String value = priceArr[1];
                        System.out.println("operatorrrr"+operator);
                        switch (operator) {
                            case "gte":
                                queryStr += "price >= " + value;
                                break;
                            case "gt":
                                queryStr += "price > " + value;
                                break;
                            case "lte":
                                queryStr += "price <= " + value;
                                break;
                            case "lt":
                                queryStr += "price < " + value;
                                break;
                        }
                        if (i < priceValues.length - 1) {
                            queryStr += " AND ";
                        }
                    }
                }
            }

            // Add filtering by color
            if (params.containsKey("color")) {
                String color = params.get("color");
                if (!queryStr.isEmpty()) {
                    queryStr += " AND ";
                }
                queryStr += "color = '" + color + "'";
            }
            // Sorting
            String sortBy = params.getOrDefault("sort", "-created_at");
            String[] sortByFields = sortBy.split(",");
            String orderBy = Arrays.stream(sortByFields)
                    .map(field -> field.startsWith("-") ? field.substring(1) + " DESC" : field + " ASC")
                    .collect(Collectors.joining(", "));

            // Limiting the fields
            String fields = params.getOrDefault("fields", "*");

            // Pagination
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int limit = Integer.parseInt(params.getOrDefault("limit", "10"));
            int offset = (page - 1) * limit;

            String query = "SELECT " + fields + " FROM Product";
            if (!queryStr.isEmpty()) {
                query += " WHERE " + queryStr;
            }
            query += " ORDER BY " + orderBy + " LIMIT " + limit + " OFFSET " + offset;

            List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Product.class));

            // Get the count of all products for pagination
            int productCount = 0;
            if (!queryStr.isEmpty()) {
                productCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Product WHERE " + queryStr, Integer.class);
            } else {
                productCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Product", Integer.class);
            }

            if (offset >= productCount) {
                throw new RuntimeException("This Page does not exists");
            }

            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(productCount))
                    .body(products);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/{id}/ratings")
    public ResponseEntity<Rating> addRatingToProduct(@PathVariable Long id, @RequestBody Rating rating) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();
        rating.setProduct(product);
     Rating rtng =   ratingRepository.save(rating);

        return ResponseEntity.status(HttpStatus.CREATED).body(rtng);
    }




}
