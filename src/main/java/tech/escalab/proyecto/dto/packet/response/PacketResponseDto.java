package tech.escalab.proyecto.dto.packet.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.escalab.proyecto.dto.product.request.ProductRequestDto;
import tech.escalab.proyecto.model.packet.Packet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class PacketResponseDto {

    private UUID uuid;
    private String code;
    private Double weight;
    private LocalDateTime schedule;
    private String status;
    private UUID truckUuid;
    private boolean deleted;

    private List<ProductRequestDto> products;


    public static PacketResponseDto mapToResponseDto(Packet packet) {
        PacketResponseDto responseDto = new PacketResponseDto();
        responseDto.setUuid(packet.getUuid());
        responseDto.setCode(packet.getCode());
        responseDto.setWeight(packet.getWeight());
        responseDto.setSchedule(packet.getSchedule());
        responseDto.setStatus(packet.getStatus().name()); // Suponiendo que getStatus() devuelve un enum
        responseDto.setTruckUuid(packet.getTruck().getUuid());
        responseDto.setDeleted(packet.isDeleted());

        // Mapear los productos asociados al paquete
        List<ProductRequestDto> productDtos = packet.getProducts().stream()
                .map(ProductRequestDto::mapToDto)
                .collect(Collectors.toList());
        responseDto.setProducts(productDtos);
        return responseDto;
    }
}
