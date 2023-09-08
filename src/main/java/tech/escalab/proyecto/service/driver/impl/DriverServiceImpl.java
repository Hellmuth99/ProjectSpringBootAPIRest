package tech.escalab.proyecto.service.driver.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.dto.truck.request.TruckRequestDto;
import tech.escalab.proyecto.dto.truck.response.TruckResponseDto;
import tech.escalab.proyecto.exception.Response;
import tech.escalab.proyecto.model.driver.Driver;
import tech.escalab.proyecto.model.packet.Packet;
import tech.escalab.proyecto.model.packet.StatusPacketEnum;
import tech.escalab.proyecto.model.truck.Truck;
import tech.escalab.proyecto.repository.driver.DriverRepositoryJpa;
import tech.escalab.proyecto.repository.packet.PacketRepositoryJpa;
import tech.escalab.proyecto.repository.truck.TruckRepositoryJpa;
import tech.escalab.proyecto.service.driver.DriverService;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {

    //private DriverRepositoryJpa driverRepositoryJpa;
    //private DriverMapper personMapper;

    DriverRepositoryJpa driverRepositoryJpa;
    PacketRepositoryJpa packetRepositoryJpa;

    TruckRepositoryJpa truckRepositoryJpa;


    @Override
    public List<?> findAllDriver() {
        System.out.println("--llegue service impl");

        List<?> driver = driverRepositoryJpa.findAll();
        if (driver.isEmpty()) {
            List<String> messageList = new ArrayList<>();
            messageList.add("No existen registros.");
            return messageList;
        }

        return driver;

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
    public ResponseEntity<?> findByIdDriver(UUID uuid) {
        System.out.println("LLEGUE AL BY ID");
        Optional<Driver> driverOptional = driverRepositoryJpa.findById(uuid);

        if (driverOptional.isPresent()) {
            return new ResponseEntity<>(driverOptional.get(), HttpStatus.OK);
        } else {
            Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), "No se encontró el conductor");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

   /* @Override
    public List<UsuarioRequest> getUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioRequest> usuariosDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosDto.add(UsuarioRequest.mapToDto(usuario));
        }

        return usuariosDto;
    }*/


    @Override
    public ResponseEntity<?> saveDriver(DriverRequestDto driverRequestDto) {
        System.out.println("LLEGUE saveDriver---------");
        // System.out.println("--llegue request TruckResponseDto"+truckRequestDto);

        // Validar si ya existe un conductor con el mismo código
        Optional<Driver> existingDriver = driverRepositoryJpa.findByCode(driverRequestDto.getCode());

        if (existingDriver.isPresent()) {
            // Ya existe un conductor con el mismo código, devolver mensaje de error
            Response errorResponse = new Response(HttpStatus.BAD_REQUEST.value(), "Ya existe un conductor con el mismo código");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

      Driver driverEntity = DriverRequestDto.mapToEntity(driverRequestDto);


        driverRepositoryJpa.save(driverEntity);

        Response response = new Response(HttpStatus.OK.value(), "Conductor creado con éxito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }


    @Override
    public ResponseEntity<?> deleteDriver(UUID driverUuid) {

        Optional<Driver> driverFound = driverRepositoryJpa.findById(driverUuid);

        if (driverFound.isPresent()) {
            // El camión existe, se puede eliminar
            Driver driveToDelete = driverFound.get(); // Obtener el objeto Truck del Optional
            driverRepositoryJpa.delete(driveToDelete);
            Response response = new Response(HttpStatus.OK.value(), "Conductor " + driverUuid + " eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // No se pudo encontrar el camión con el UUID proporcionado
            Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), "No se encontró el conductor con id:"+ driverUuid);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    }

    public boolean areAllPacketsDeliveredOrCancelled(UUID driverUuid) {
        List<Packet> packets = packetRepositoryJpa.findByTruckDriverUuid(driverUuid);

        // Verificar si todos los paquetes tienen estado "DELIVERED" o "CANCELLED"
        return packets.stream().allMatch(packet ->
                packet.getStatus() == StatusPacketEnum.DELIVERED ||
                        packet.getStatus() == StatusPacketEnum.CANCELLED
        );
    }

}
