package com.example.demo.repositories.specifications;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {

    public static Specification<Product> titleContains(String word) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%"+word+"%");
    }

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Product> priceLesserThanOrEq(BigDecimal value) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }

}
