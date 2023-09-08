package tech.escalab.proyecto.service.packet;

import org.springframework.http.ResponseEntity;
import tech.escalab.proyecto.dto.driver.request.DriverRequestDto;
import tech.escalab.proyecto.dto.packet.request.PacketRequestDto;

import java.util.List;
import java.util.UUID;

public interface PacketService {

    ResponseEntity<?> savePacket(PacketRequestDto request);

    ResponseEntity<?> changeStatePacket(PacketRequestDto request);

    List<?> findAllPacket();

    ResponseEntity<?> deleteProductFromPackage(UUID packageUuid, UUID productUuid);
}
