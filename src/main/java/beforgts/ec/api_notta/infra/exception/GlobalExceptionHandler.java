package beforgts.ec.api_notta.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler { // classe gerenciada pelo spring para o tratamento de exceções. não foi abordada toda a implementação

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseError> handleUserNotFoundException(UserNotFoundException e){
        return ResponseEntity.status(404).body(new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()));
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ResponseError> handleInvalidPasswordException(InvalidPasswordException e){
        return ResponseEntity.status(400).body(new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDate.now()));
    }
    @ExceptionHandler(UserNotActivatedException.class)
    public ResponseEntity<ResponseError> handleUserNotActivatedException(UserNotActivatedException e){
        return ResponseEntity.status(400).body(new ResponseError(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDate.now()));
    }
}
