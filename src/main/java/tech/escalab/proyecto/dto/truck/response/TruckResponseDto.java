package tech.escalab.proyecto.dto.truck.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.escalab.proyecto.model.truck.Truck;

import java.util.UUID;
@Getter
@Setter
@ToString
public class TruckResponseDto {

    private UUID uuid;


    private String code;

    public static TruckResponseDto toThis(Truck truck) {

        /*if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("El código del camión no puede estar vacío.");
        }*/


        var response = new TruckResponseDto();
        response.setUuid(truck.getUuid());
        response.setCode(truck.getCode());


        return response;
    }
}
