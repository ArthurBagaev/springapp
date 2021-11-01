package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OldProductRepository {

    private List<Product> products;

   /* @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread", 40));
        products.add(new Product(2L, "Milk", 90));
        products.add(new Product(3L, "Cheese", 70));
    } товар в базе лежит */

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }


    public List<Product> findAll() {
        return products;
    }

    public void save(Product product) {
        products.add(product);
    }

    public void setProducts(Product product) {
        products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .peek(p -> p.setPrice(product.getPrice()))
                .peek(p -> p.setTitle(product.getTitle()))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            if (product.getId().equals(id))
                iter.remove();
                return;
        }
    }
}
