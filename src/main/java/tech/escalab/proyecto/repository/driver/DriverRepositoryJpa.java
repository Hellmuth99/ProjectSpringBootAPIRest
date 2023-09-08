package tech.escalab.proyecto.repository.driver;

import org.springframework.stereotype.Repository;
import tech.escalab.proyecto.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepositoryJpa extends JpaRepository<Driver, UUID> {

    Optional<Driver> findByCode(String code);

    Optional<Driver> findByUuid(UUID uuid);


}
