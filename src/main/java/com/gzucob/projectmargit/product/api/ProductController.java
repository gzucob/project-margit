package com.gzucob.projectmargit.product.api;

import com.gzucob.projectmargit.product.dto.CreateProductRequest;
import com.gzucob.projectmargit.product.dto.ProductResponse;
import com.gzucob.projectmargit.product.dto.ProductSearchByNameResponse;
import com.gzucob.projectmargit.product.domain.Product;
import com.gzucob.projectmargit.product.domain.ProductService;
import com.gzucob.projectmargit.product.dto.UpdateProductRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct (@Valid @RequestBody CreateProductRequest createProductRequest) {
        Product product = productService.createProduct(createProductRequest);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSearchByNameResponse> deleteById (@PathVariable UUID id) {
        return ResponseEntity.ok(productService.deleteById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductById (@Valid @PathVariable UUID id,
                                                      @RequestBody UpdateProductRequest updateProductRequest) {
       return ResponseEntity.ok(productService.updateProductById(id, updateProductRequest));
    }
}
