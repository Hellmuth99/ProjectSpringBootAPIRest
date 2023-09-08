package tech.escalab.proyecto.dto.driver.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class DriverResponseDto {

    private UUID uuid;

    private String name;

}
