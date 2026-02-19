package com.gzucob.projectmargit.product.domain;

import com.gzucob.projectmargit.product.dto.CreateProductRequest;
import com.gzucob.projectmargit.product.dto.ProductResponse;
import com.gzucob.projectmargit.product.dto.ProductSearchByNameResponse;
import com.gzucob.projectmargit.product.dto.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct (CreateProductRequest createProductRequest) {
        Product product = new Product(
                createProductRequest.name(),
                createProductRequest.price(),
                createProductRequest.quantity()
        );
        return productRepository.save(product);
    }

    public List<ProductResponse> findAllProducts () {
        return productRepository.findAll().stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQuantity(),
                        p.getProductAddAt()
                ))
                .toList();
    }

    public List<ProductSearchByNameResponse> searchByName (String name) {
        return productRepository.findByNameContainingIgnoreCase(name).stream()
                .map(p -> new ProductSearchByNameResponse(
                        p.getId(),
                        p.getName(),
                        ""
                ))
                .toList();
    }

    public ProductSearchByNameResponse deleteById (UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.deleteById(id);
        return new ProductSearchByNameResponse(
                product.getId(),
                product.getName(),
                "Product deleted successfully"
        );
    }

    public Product updateProductById(UUID id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        product.updateProduct(
                updateProductRequest.name(),
                updateProductRequest.price(),
                updateProductRequest.quantity()
        );
        return productRepository.save(product);
    }
}
