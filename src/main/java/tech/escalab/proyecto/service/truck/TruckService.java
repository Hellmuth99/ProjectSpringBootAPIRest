package tech.escalab.proyecto.service.truck;

import org.springframework.http.ResponseEntity;
import tech.escalab.proyecto.dto.truck.request.TruckRequestDto;
import tech.escalab.proyecto.dto.truck.response.TruckResponseDto;
import tech.escalab.proyecto.model.truck.Truck;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TruckService {

    List<?> findAll();
    ResponseEntity<?> findByUuid(UUID truckUuid);
  //ResponseEntity<?> findByUuid(UUID uuid);
  ResponseEntity<?>save(TruckRequestDto request);

    ResponseEntity<?> delete(UUID truckUuid);
}
