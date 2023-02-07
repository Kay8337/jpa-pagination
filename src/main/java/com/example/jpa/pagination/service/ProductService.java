package com.example.jpa.pagination.service;

import com.example.jpa.pagination.Repo.ProductRepository;
import com.example.jpa.pagination.entity.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //this method is executed after the application is started @postconstruct is used for any
    //initialization after the depencey injection is done, it's the alternative of @initMethod
    @PostConstruct
    public void initDB(){
        List<Product> products = IntStream.rangeClosed(1,200)
                .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }


    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }

    public List<Product> findAllProductBySort(String field){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> findAllProductWithPagination(int offSet, int pageSize) {
        return  productRepository.findAll(PageRequest.of(offSet, pageSize));
    }

    public Page<Product> findAllProductWithPaginationWithSort(int offSet, int pageSize, String field) {
        return productRepository.findAll(PageRequest.of(offSet, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)));
    }
}
