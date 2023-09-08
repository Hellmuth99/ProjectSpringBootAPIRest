package tech.escalab.proyecto.service.driver;

import org.springframework.http.ResponseEntity;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.dto.truck.request.TruckRequestDto;
import tech.escalab.proyecto.dto.truck.response.TruckResponseDto;

import java.util.List;
import java.util.UUID;

public interface DriverService {

    List<?> findAllDriver();
    ResponseEntity<?> findByIdDriver(UUID driverUuid);
    ResponseEntity<?> saveDriver(DriverRequestDto request);

    ResponseEntity<?> deleteDriver(UUID driverUuid);


    //List<?> managedPacket(UUID uuid);
    public boolean areAllPacketsDeliveredOrCancelled(UUID uuid);

}
