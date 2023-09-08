package tech.escalab.proyecto.repository.product;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.escalab.proyecto.model.packet.Packet;
import tech.escalab.proyecto.model.product.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepositoryJpa extends CrudRepository<Product, UUID> {


}
