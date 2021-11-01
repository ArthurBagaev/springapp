package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.OldProductRepository;
import com.example.demo.repositories.ProductRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<Product> getItemsWithPagingAndFiltering(Specification<Product> specifications, Pageable pageable) {
        return productRepository.findAll(specifications, pageable);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    public Product incrementViewsCounter(Product product) {
        product.setViews(product.getViews() + 1);
        return productRepository.save(product);
    }

    public List<Product> getTop3List() {
        return productRepository.getTop3Products().stream().limit(3).collect(Collectors.toList());
    }

}
