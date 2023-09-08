package tech.escalab.proyecto.dto.driver.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.proyecto.model.driver.Driver;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequestDto {
    private UUID uuid;

    @Schema(example = "Jaime")
    @Size(min = 5, max = 10, message = "El tamaño del nombre no es correcto")
    @NotEmpty(message = "El nombre del conductor es obligatorio")
    private String name;

    @NotEmpty(message = "El codigo del conductor es obligatorio")
    private String code;

    @NotEmpty(message = "El correo electrónico del usuario es obligatorio")
    @Email(message = "Por favor, proporciona una dirección de correo electrónico válida.")
    private String email;
    @Schema(example = "987654321")
    @Size(min = 9, max = 9, message = "El celular debe tener 9 caracteres")
    private String cellphone;

    private boolean is_deleted;

    public static DriverRequestDto mapToDto(Driver entity) {
        DriverRequestDto dto = new DriverRequestDto();

        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setEmail(entity.getEmail());
        dto.setCellphone(entity.getCellphone());
        dto.set_deleted(entity.is_deleted());

        return dto;
    }


    public static Driver mapToEntity(DriverRequestDto dto){
        Driver driverEntity = new Driver();

        driverEntity.setName(dto.getName());
        driverEntity.setCode(dto.getCode());
        driverEntity.setEmail(dto.getEmail());
        driverEntity.setCellphone(dto.getCellphone());
        driverEntity.set_deleted(dto.is_deleted());

        return driverEntity;

    }


}
