package tech.escalab.proyecto.dto.packet.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import tech.escalab.proyecto.dto.product.request.ProductRequestDto;
import tech.escalab.proyecto.model.driver.Driver;
import tech.escalab.proyecto.model.packet.Packet;
import tech.escalab.proyecto.model.packet.StatusPacketEnum;
import tech.escalab.proyecto.model.product.Product;
import tech.escalab.proyecto.model.truck.Truck;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacketRequestDto {


    @NotEmpty(message = "El codigo del camion no puede estar vacio")
    private String code;

    @NotNull(message = "El campo weight no puede ser nulo")
    @Min(value = 0, message = "El peso debe ser mayor o igual a 0")
    private Double weight; // Usar Double en lugar de double para permitir nulos

    @NotNull(message = "Se debe asociar un conductor al paquete")
    private UUID truckUuid; // Cambiamos el nombre y el tipo de dato


    private StatusPacketEnum status;


   /* @Valid
    private List<Product> products;*/
   @Valid
   private List<ProductRequestDto> products;


    public static Packet mapToEntity(PacketRequestDto packet){

        System.out.println("paseeee por aqui");

        Packet packetEntity = new Packet();

        packetEntity.setCode(packet.getCode());
        packetEntity.setWeight(packet.getWeight());
        packetEntity.setStatus(packet.getStatus());
        //packetEntity.setProducts(packet.getProducts());


        // Configura el UUID del conductor en el camión
        Truck truck = new Truck();
        truck.setUuid(packet.getTruckUuid());
        // Asigna el conductor al camión
        packetEntity.setTruck(truck);



        return packetEntity;

    }



}
