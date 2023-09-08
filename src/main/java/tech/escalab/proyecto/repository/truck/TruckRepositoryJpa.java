package tech.escalab.proyecto.repository.truck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.escalab.proyecto.model.packet.Packet;
import tech.escalab.proyecto.model.truck.Truck;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TruckRepositoryJpa  extends CrudRepository<Truck, UUID> {

    List<Truck> findAll();

    Optional<Truck> findById(UUID code);

    boolean existsByCode(String code);

    boolean existsByDriverUuid(UUID driverUuid);

    Optional<Truck> findByDriverUuid(UUID driverUuid);

    //Optional<Truck> findByName(String name);
}
