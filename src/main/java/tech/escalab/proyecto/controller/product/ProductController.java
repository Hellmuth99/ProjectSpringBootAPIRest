package tech.escalab.proyecto.controller.product;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.dto.product.request.ProductRequestDto;
import tech.escalab.proyecto.service.packet.impl.PacketServiceImpl;
import tech.escalab.proyecto.service.product.impl.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {


    ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService){this.productService=productService;}


    @GetMapping
    public List<?> findAllProducts() {

        return productService.findAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {

        return productService.saveProduct(productRequestDto);
    }

}
