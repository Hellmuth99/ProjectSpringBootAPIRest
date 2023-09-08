package tech.escalab.proyecto.service.truck.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.escalab.proyecto.dto.truck.request.TruckRequestDto;
import tech.escalab.proyecto.dto.truck.response.TruckResponseDto;
import tech.escalab.proyecto.exception.Response;
import tech.escalab.proyecto.exception.general.NotFoundException;
import tech.escalab.proyecto.model.driver.Driver;
import tech.escalab.proyecto.model.truck.Truck;
import tech.escalab.proyecto.repository.driver.DriverRepositoryJpa;
import tech.escalab.proyecto.repository.truck.TruckRepositoryJpa;
import tech.escalab.proyecto.service.truck.TruckService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TruckServiceImpl implements TruckService {


    TruckRepositoryJpa truckRepositoryJpa;

    DriverRepositoryJpa driverRepositoryJpa;



    @Override
    public List<?> findAll() {
        System.out.println("--llegue service--TRUCK impl");

        List<?> trucks = truckRepositoryJpa.findAll();
        if (trucks.isEmpty()) {
            List<String> messageList = new ArrayList<>();
            messageList.add("No se encontraron camiones en la base de datos.");
            return messageList;
        }

        return trucks;
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
    private Truck findById(UUID truckUuid) {


        return truckRepositoryJpa.findById(truckUuid)
                .orElseThrow(() ->  new NotFoundException("No se encontró el camión"));



    }
   @Override
    public ResponseEntity<?> findByUuid(UUID uuid) {
        System.out.println("--llegue service-VfindByUuid");
        Optional<Truck> truckOptional = truckRepositoryJpa.findById(uuid);

        if (truckOptional.isPresent()) {
            return new ResponseEntity<>(truckOptional.get(), HttpStatus.OK);
        } else {
            Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), "No se encontró el camión");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

   /* @Override
    public TruckResponseDto findByUuid(UUID truckUuid) {

        return TruckResponseDto.toThis(
                findById(truckUuid)
        );
    }*/

   /* @Override
    public TruckResponseDto  findByUuid(UUID truckUuid) {

        Optional<Truck> truck = truckRepositoryJpa.findById(truckUuid);

        if(truck == null)  {
            Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), "No se encontraró el usuario");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } else {
            System.out.println("-si-encontre-user");
            return new ResponseEntity<>(truck, HttpStatus.OK);

        }



    }*/


    @Override
    public ResponseEntity<?> save(TruckRequestDto truckRequestDto) {


        // Verificar que el código del camión no exista
        String code = truckRequestDto.getCode();
        if (truckRepositoryJpa.existsByCode(code)) {
            Response response = new Response(HttpStatus.BAD_REQUEST.value(), "El código del camión ya existe.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        // Obtén el UUID del conductor desde el DTO
        UUID driverUuid = truckRequestDto.getDriverUuid();

        // Verifica si el conductor ya está asignado a otro camión
        boolean driverAlreadyAssigned = truckRepositoryJpa.existsByDriverUuid(driverUuid);

        if (driverAlreadyAssigned) {
            // El conductor ya está asignado a otro camión, maneja el error o lanza una excepción
            Response response = new Response(HttpStatus.BAD_REQUEST.value(), "El conductor ya está asignado a otro camión");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        // Si el conductor no está asignado a otro camión, continúa con la creación del camión
        Driver driverExists = driverRepositoryJpa.findByUuid(driverUuid).orElse(null);

        if (driverExists == null) {
            // El conductor no existe, maneja el error o lanza una excepción
            Response response = new Response(HttpStatus.NOT_FOUND.value(), "Conductor no encontrado para el ID: " + driverUuid);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


       /* // Obtén el ID del conductor desde el DTO (supongamos que el DTO tiene un campo conductorId)
        //UUID driverUuid = truckRequestDto.getDriverUuid();
        System.out.println("truckRequestDto"+truckRequestDto);
        UUID driverUuid = truckRequestDto.getDriverUuid();
        System.out.println("driverUuid"+driverUuid);
        // Verifica si el conductor existe
        Driver driverExists = driverRepositoryJpa.findByUuid(driverUuid).orElse(null);

        if (driverExists == null) {
            // El conductor no existe, puedes manejar el error o lanzar una excepción
            //throw new ConductorNotFoundException("Conductor no encontrado para el ID: " + conductorId);
            Response response = new Response(HttpStatus.OK.value(), "Conductor no encontrado para el ID: " + driverUuid);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }*/



        Truck truckEntity = TruckRequestDto.mapToEntity(truckRequestDto);
        System.out.println("truckEntity"+truckEntity);

        truckRepositoryJpa.save(truckEntity);

        Response response = new Response(HttpStatus.OK.value(), "Camión creado con éxito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
      //  return TruckResponseDto.toThis(truckEntity);
    }


    @Override
    public ResponseEntity<?> delete(UUID truckUuid) {Optional<Truck> truckFound = truckRepositoryJpa.findById(truckUuid);

        if (truckFound.isPresent()) {
            // El camión existe, se puede eliminar
            Truck truckToDelete = truckFound.get(); // Obtener el objeto Truck del Optional
            truckRepositoryJpa.delete(truckToDelete);
            Response response = new Response(HttpStatus.OK.value(), "Camión " + truckUuid + " eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // No se pudo encontrar el camión con el UUID proporcionado
            Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), "No se encontró el camión");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    }
}
