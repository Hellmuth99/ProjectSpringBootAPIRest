package tech.escalab.proyecto.repository.packet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.escalab.proyecto.model.driver.Driver;
import tech.escalab.proyecto.model.packet.Packet;
import tech.escalab.proyecto.model.truck.Truck;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacketRepositoryJpa extends CrudRepository<Packet, UUID> {

    Optional<Packet> findByCode(String code);

    List<Packet> findAll();

    List<Packet> findByTruckDriverUuid(UUID driver_uuid);
}
