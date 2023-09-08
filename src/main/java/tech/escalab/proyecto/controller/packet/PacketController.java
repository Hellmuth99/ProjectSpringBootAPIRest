package tech.escalab.proyecto.controller.packet;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.escalab.proyecto.dto.packet.request.PacketRequestDto;

import tech.escalab.proyecto.service.packet.impl.PacketServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/packet")
public class PacketController {
    PacketServiceImpl packetService;

    public PacketController(PacketServiceImpl packetService){this.packetService=packetService;}

    @GetMapping
    public List<?> findAllPacket() {

        return packetService.findAllPacket();
    }

    @PostMapping
    public ResponseEntity<?> savePacket(@Valid @RequestBody PacketRequestDto packetRequestDto) {

        return packetService.savePacket(packetRequestDto);
    }

    @PutMapping
    public ResponseEntity<?> changeStatePacket(@RequestBody PacketRequestDto packetRequestDto) {
        // Aquí debes implementar la lógica para cambiar el estado del paquete
        // Obtener el paquete por su ID, actualizar el estado y guardar el cambio

        return packetService.changeStatePacket(packetRequestDto);
      //  return new ResponseEntity<>("Estado del paquete cambiado con éxito", HttpStatus.OK);
    }

    @DeleteMapping("/{packageUuid}/{productUuid}")
    public ResponseEntity<?> deleteProductFromPackage(
            @PathVariable UUID packageUuid,
            @PathVariable UUID productUuid
    ) {
        return packetService.deleteProductFromPackage(packageUuid, productUuid);

    }

}
