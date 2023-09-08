package tech.escalab.proyecto.controller.driver;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.dto.truck.request.TruckRequestDto;
import tech.escalab.proyecto.dto.truck.response.TruckResponseDto;
import tech.escalab.proyecto.service.driver.impl.DriverServiceImpl;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    //private final DriverService driverService;
    DriverServiceImpl driverService;

    public DriverController(DriverServiceImpl driverService){this.driverService=driverService;}



    @GetMapping
    public List<?> findAllDriver() {

        return driverService.findAllDriver();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findByIdDriver(
            @PathVariable UUID uuid
    ) {

        return driverService.findByIdDriver(uuid);
    }

    @PostMapping
    public ResponseEntity<?> saveDriver(@Valid @RequestBody DriverRequestDto driverRequestDto) {

        return driverService.saveDriver(driverRequestDto);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteDriver (@PathVariable UUID uuid){

        return driverService.deleteDriver(uuid);
    }


    @GetMapping("/managedPacket/{uuid}")
    public ResponseEntity<String> checkDriverPackets (@PathVariable UUID uuid){

       // return driverService.managedPacket(uuid);

        boolean allPacketsDeliveredOrCancelled = driverService.areAllPacketsDeliveredOrCancelled(uuid);

        if (allPacketsDeliveredOrCancelled) {
            return ResponseEntity.ok("Todos los paquetes tienen estado DELIVERED o CANCELLED.");
        } else {
            return ResponseEntity.ok("No todos los paquetes tienen estado DELIVERED o CANCELLED.");
        }
    }


}
