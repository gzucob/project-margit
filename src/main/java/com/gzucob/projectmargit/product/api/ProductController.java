package com.gzucob.projectmargit.product.api;

import com.gzucob.projectmargit.product.dto.ProductRequest;
import com.gzucob.projectmargit.product.dto.ProductResponse;
import com.gzucob.projectmargit.product.dto.ProductSearchByNameResponse;
import com.gzucob.projectmargit.product.domain.Product;
import com.gzucob.projectmargit.product.domain.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct (@RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll()  {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductSearchByNameResponse>> searchByName (@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }
}
