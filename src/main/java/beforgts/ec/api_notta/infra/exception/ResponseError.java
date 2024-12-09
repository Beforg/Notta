package beforgts.ec.api_notta.infra.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public record ResponseError(String message , HttpStatus httpStatus, LocalDate date) {
}
