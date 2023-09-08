package tech.escalab.proyecto.controller.truck;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.proyecto.dto.truck.request.TruckRequestDto;
import tech.escalab.proyecto.dto.truck.response.TruckResponseDto;
import tech.escalab.proyecto.service.driver.impl.DriverServiceImpl;
import tech.escalab.proyecto.service.truck.impl.TruckServiceImpl;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/truck")
public class TruckController {


    TruckServiceImpl truckService;

    public TruckController(TruckServiceImpl truckService){this.truckService=truckService;}



    @GetMapping
    public List<?> findAll() {

        return truckService.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> findById(
            @PathVariable UUID uuid
    ) {

        return truckService.findByUuid(uuid);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TruckRequestDto truckRequestDto) {

        return truckService.save(truckRequestDto);
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete (@PathVariable UUID uuid){

        return truckService.delete(uuid);
    }



}
