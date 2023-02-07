package com.example.jpa.pagination.controller;

import com.example.jpa.pagination.dto.APIResponse;
import com.example.jpa.pagination.entity.Product;
import com.example.jpa.pagination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public APIResponse<List<Product>> getAllProducts(){
        List<Product> allProducts = productService.findAllProduct();
        return new APIResponse<>(allProducts.size(), allProducts);
    }
    @GetMapping("/{field}")
    public APIResponse<List<Product>> getAllProductsBySort(@PathVariable String field){
        List<Product> allProducts = productService.findAllProductBySort(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }
    @GetMapping("/pagination/{offSet}/{pageSize}")
    public APIResponse<Page<Product>> getAllProductsByPagination(@PathVariable int offSet, @PathVariable int pageSize){
        Page<Product> allProducts = productService.findAllProductWithPagination(offSet, pageSize);
        return new APIResponse<>(allProducts.getSize(), allProducts);
    }

    @GetMapping("/pagination/{offSet}/{pageSize}/{field}")
    public APIResponse<Page<Product>> getAllProductsByPagination(@PathVariable int offSet, @PathVariable int pageSize, @PathVariable String field){
        Page<Product> allProducts = productService.findAllProductWithPaginationWithSort(offSet, pageSize, field);
        return new APIResponse<>(allProducts.getSize(), allProducts);
    }
}


