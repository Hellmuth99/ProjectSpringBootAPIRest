package tech.escalab.proyecto.service.product.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.escalab.proyecto.dto.product.request.ProductRequestDto;
import tech.escalab.proyecto.exception.Response;

import tech.escalab.proyecto.repository.product.ProductRepositoryJpa;

import tech.escalab.proyecto.service.product.ProductService;

import java.util.List;



@Service
public class ProductServiceImpl implements ProductService {
    ProductRepositoryJpa productRepositoryJpa;

    public ProductServiceImpl(ProductRepositoryJpa productRepositoryJpa){

        this.productRepositoryJpa = productRepositoryJpa;
    }

    @Override
    public List<?> findAllProducts() {
        System.out.println("--llegue service impl");

        /*List<?> driver = productRepositoryJpa.findAll();
        if (driver.isEmpty()) {
            List<String> messageList = new ArrayList<>();
            messageList.add("No existen registros.");
            return messageList;
        }*/

        return null;

        //return driverRepositoryJpa.findAll();
      /*  return driverRepositoryJpa.findAll().stream()
                .map(person -> personMapper.toResponseDto(person))
                .toList();*/

        /*List<Driver> drivers = driverRepositoryJpa.findAll();
        List<DriverRequestDto> driversDto = new ArrayList<>();

        for (Driver driver : drivers) {
            driversDto.add(DriverRequestDto.mapToDto(driver));
        }

        return driversDto;*/

    }

    @Override
    public ResponseEntity<?> saveProduct(ProductRequestDto productRequestDto) {

       /* Product productEntity = ProductRequestDto.mapToEntity(productRequestDto);

        productRepositoryJpa.save(productEntity);*/

        Response response = new Response(HttpStatus.OK.value(), "Producto creado con éxito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
