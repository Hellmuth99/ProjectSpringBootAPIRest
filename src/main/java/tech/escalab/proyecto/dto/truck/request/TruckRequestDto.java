package tech.escalab.proyecto.dto.truck.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.escalab.proyecto.model.driver.Driver;
import tech.escalab.proyecto.model.truck.Truck;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TruckRequestDto {


    @NotEmpty(message = "El codigo del camion no puede estar vacio")
    private String code;


    @NotNull(message = "El camión debe tener un conductor asociado")
    private UUID driverUuid; // Cambiamos el nombre y el tipo de dato


    public static TruckRequestDto mapToDto(Truck entity) {
        TruckRequestDto dto = new TruckRequestDto();
        dto.setCode(entity.getCode());

        return dto;
    }

    public static Truck mapToEntity(TruckRequestDto truck){

        System.out.println("paseeee por aqui");


        /*String code = truck.getCode();
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("El código del camión no puede estar vacío.");
        }*/

        // Verificar si el código ya existe en la base de datos
        /*Optional<Truck> existingTruck = truckRepository.findByCode(code);
        if (existingTruck.isPresent()) {
            throw new IllegalArgumentException("El código del camión ya existe.");
        }*/

        Truck truckEntity = new Truck();
        truckEntity.setCode(truck.getCode());

        // Configura el UUID del conductor en el camión
        Driver driver = new Driver();
        driver.setUuid(truck.getDriverUuid());

        // Asigna el conductor al camión
        truckEntity.setDriver(driver);

        return truckEntity;

    }
}
