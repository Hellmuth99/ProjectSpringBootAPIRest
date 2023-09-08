package tech.escalab.proyecto.service.packet.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.escalab.proyecto.dto.packet.request.PacketRequestDto;
import tech.escalab.proyecto.dto.packet.response.PacketResponseDto;
import tech.escalab.proyecto.dto.product.request.ProductRequestDto;
import tech.escalab.proyecto.exception.Response;

import tech.escalab.proyecto.model.packet.Packet;
import tech.escalab.proyecto.model.packet.StatusPacketEnum;

import tech.escalab.proyecto.model.product.Product;
import tech.escalab.proyecto.model.truck.Truck;
import tech.escalab.proyecto.repository.packet.PacketRepositoryJpa;

import tech.escalab.proyecto.repository.product.ProductRepositoryJpa;
import tech.escalab.proyecto.repository.truck.TruckRepositoryJpa;
import tech.escalab.proyecto.service.packet.PacketService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PacketServiceImpl  implements PacketService {


    private PacketRepositoryJpa packetRepositoryJpa;

    private TruckRepositoryJpa truckRepositoryJpa;

    private ProductRepositoryJpa productRepositoryJpa;

    @Override
    public List<?> findAllPacket() {


        List<Packet> packetList = packetRepositoryJpa.findAll();
        if (packetList .isEmpty()) {
            List<String> messageList = new ArrayList<>();
            messageList.add("No existen registros.");
            return messageList;
        }

        // Mapea los objetos Packet a objetos PacketResponseDto
        List<PacketResponseDto> responseDtos = packetList.stream()
                .map(packet -> PacketResponseDto.mapToResponseDto(packet))
                .collect(Collectors.toList());

        return responseDtos;

        //return packetList ;

    }

    @Override
    public ResponseEntity<?> savePacket(PacketRequestDto packetRequestDto) {


        // Validar si ya existe un conductor con el mismo código
        Optional<Packet> existingDriver = packetRepositoryJpa.findByCode(packetRequestDto.getCode());

        if (existingDriver.isPresent()) {
            // Ya existe un conductor con el mismo código, devolver mensaje de error
            Response errorResponse = new Response(HttpStatus.BAD_REQUEST.value(), "Ya existe un conductor con el mismo código");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Verificar si se proporcionó un truck_uuid
        UUID truckUuid = packetRequestDto.getTruckUuid();
        if (truckUuid == null) {
            // No se proporcionó un truck_uuid, devolver mensaje de error
            Response errorResponse = new Response(HttpStatus.BAD_REQUEST.value(), "El paquete debe tener un truck_uuid");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Verificar si el truck_uuid es válido (existe en la base de datos)
        Optional<Truck> truck = truckRepositoryJpa.findById(truckUuid);
        if (truck.isEmpty()) {
            // El truck_uuid no es válido, devolver mensaje de error
            Response errorResponse = new Response(HttpStatus.BAD_REQUEST.value(), "El truck_uuid no es válido");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Configura el estado por defecto (LOADED) para nuevos paquetes
        Packet packetEntity = PacketRequestDto.mapToEntity(packetRequestDto);
        packetEntity.setStatus(StatusPacketEnum.LOADED);


        // Crear y asociar productos al paquete
        List<Product> products = new ArrayList<>();
        for (ProductRequestDto productDTO : packetRequestDto.getProducts()) {
            Product product = new Product();
            product.setCode(productDTO.getCode());
            product.setWeight(productDTO.getWeight());
            product.setSku(productDTO.getSku());
            product.setQuantity(productDTO.getQuantity());
            product.setPacket(packetEntity);
            products.add(product);
        }

        packetEntity.setProducts(products);


        packetRepositoryJpa.save(packetEntity);

        Response response = new Response(HttpStatus.OK.value(), "Paquete creado con éxito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }


    public ResponseEntity<?> changeStatePacket(PacketRequestDto packetRequestDto) {


        Optional<Packet> optionalPacket = packetRepositoryJpa.findByCode(packetRequestDto.getCode());

        if (optionalPacket.isEmpty()) {
            return new ResponseEntity<>("Paquete no encontrado", HttpStatus.NOT_FOUND);
        }

        Packet paquete = optionalPacket.get();
        StatusPacketEnum nuevoEstadoPacket = packetRequestDto.getStatus();

        if (nuevoEstadoPacket == null) {
            return new ResponseEntity<>("Estado no válido", HttpStatus.BAD_REQUEST);
        }

        paquete.setStatus(nuevoEstadoPacket);
        packetRepositoryJpa.save(paquete);

        return new ResponseEntity<>("Estado del paquete cambiado con éxito", HttpStatus.OK);

       /* var newPacket =   packetRepositoryJpa.save(paquete);
        return new ResponseEntity<>(newPacket, HttpStatus.OK);*/

    }




    public ResponseEntity<?> deleteProductFromPackage(UUID packageUuid, UUID productUuid) {
        Optional<Packet> optionalPacket = packetRepositoryJpa.findById(packageUuid);
        Optional<Product> optionalProduct = productRepositoryJpa.findById(productUuid);

        if (!optionalPacket.isPresent()) {
            return ResponseEntity.badRequest().body("Paquete incorrecto. No existe.");
        }

        Packet packet = optionalPacket.get();

        if (!optionalProduct.isPresent()) {
            return ResponseEntity.badRequest().body("Producto incorrecto. No existe.");
        }

        UUID productId = optionalProduct.get().getUuid();

        if (packet.getStatus() != StatusPacketEnum.LOADED) {
            return ResponseEntity.badRequest().body("El paquete no está en estado LOADED.");
        }


        boolean productBelongsToPacket = packet.getProducts().stream()
                .anyMatch(product -> product.getUuid().equals(productId));
        System.out.println("productBelongsToPacket " + productBelongsToPacket);

        if (!productBelongsToPacket) {
            return ResponseEntity.badRequest().body("El producto no pertenece al paquete.");
        }

        packet.removeProduct(productId);
        packetRepositoryJpa.save(packet);

        return ResponseEntity.ok("Producto eliminado del paquete.");
    }

}
