package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.specifications.ProductSpec;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductsController {


    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Principal principal, Model model,
                                  @RequestParam(value = "word", required = false) String word,
                                  @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                  @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice) {


        //principal - юзер
        if (principal != null) {
            User user = userRepository.findByUsername(principal.getName());
            model.addAttribute("name", user.getName());
            System.out.println(user.getEmail());
        }


        Specification<Product> specification = Specification.where(null);  //создаём пустую спецификацию которая никак не повлияет на наш поиск
        if (word != null) {
            specification = specification.and(ProductSpec.titleContains(word));
        }
        if (minPrice != null) {
            specification = specification.and(ProductSpec.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpec.priceLesserThanOrEq(maxPrice));
        }

        Product product = new Product();
        model.addAttribute("top3List", productService.getTop3List());
        model.addAttribute("products", productService.getItemsWithPagingAndFiltering(specification, PageRequest.of(0, 100)).getContent()); //в объект просихоит добавление данных
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/add")
    @Secured(value = "ROLE_ADMIN")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-edit";
    }    @GetMapping("/edit/{id}")
    @Secured(value = "ROL E_ADMIN")
    public String showEditProductForm(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit")
    @Secured(value = "ROLE_ADMIN")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getById(id);
        product = productService.incrementViewsCounter(product);
        model.addAttribute("oneproduct", product);
        return "product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable (value = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable(value = "id") Long id) {
        return productService.getById(id);
    }

}
