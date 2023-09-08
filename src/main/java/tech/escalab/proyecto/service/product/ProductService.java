package tech.escalab.proyecto.service.product;

import org.springframework.http.ResponseEntity;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.dto.product.request.ProductRequestDto;

import java.util.List;

public interface ProductService {

    List<?> findAllProducts();

    ResponseEntity<?> saveProduct(ProductRequestDto request);
}
