package tech.escalab.proyecto.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomainExceptionCode {

    PERSON_NOT_FOUND(201, 404, "person_not_found"),
    ADDRESS_NOT_FOUND(202, 404, "address_not_found");

    private Integer codeApp;

    private Integer statusCode;

    private String messageKey;
}
