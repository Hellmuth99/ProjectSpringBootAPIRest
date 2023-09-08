package tech.escalab.proyecto.dto.product.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.model.driver.Driver;
import tech.escalab.proyecto.model.product.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {


    private UUID uuid;

    @NotEmpty(message = "El codigo del producto es obligatorio")
    private String code;
    @NotNull(message = "El peso del producto es obligatorio")
    private double weight;
    private String sku;
    @NotNull(message = "la cantidad es obligatoria")
    private Integer  quantity;
    private boolean isDeleted;
    private LocalDateTime deletedAt;


    public static ProductRequestDto mapToDto(Product product) {
        ProductRequestDto productDto = new ProductRequestDto();
        productDto.setUuid(product.getUuid());
        productDto.setCode(product.getCode());
        productDto.setWeight(product.getWeight());
        productDto.setSku(product.getSku());
        productDto.setQuantity(product.getQuantity());
        return productDto;
    }



    /*public static ProductRequestDto mapToEntity(Product dto){
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setCode(dto.getCode());
        productRequestDto.setWeight(dto.getWeight());
        productRequestDto.setSku(dto.getSku());
        productRequestDto.setQuantity(dto.getQuantity());



        return productRequestDto;

    }*/

    public static List<Product> mapToEntity(List<ProductRequestDto> dtos) {
        return dtos.stream()
                .map(dto -> {
                    Product productEntity = new Product();
                    productEntity.setCode(dto.getCode());
                    productEntity.setWeight(dto.getWeight());
                    productEntity.setSku(dto.getSku());
                    productEntity.setQuantity(dto.getQuantity());
                    return productEntity;
                })
                .collect(Collectors.toList());
    }
}
