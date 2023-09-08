
package tech.escalab.proyecto.exception;

        import org.springframework.dao.DataIntegrityViolationException;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.HttpRequestMethodNotSupportedException;
        import org.springframework.web.bind.MethodArgumentNotValidException;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.context.request.WebRequest;
        import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

        import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequestHandler(MethodArgumentNotValidException ex,
                                                    WebRequest request){

        ErrorHttp message = new ErrorHttp(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                DetalleError.mapearError(ex.getFieldErrors()),
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorHttp message = new ErrorHttp(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                "Correo ya existente",
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                   WebRequest request) {
        ErrorHttp message = new ErrorHttp(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "El valor proporcionado no es un UUID válido",
                request.getDescription(false)
        );

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String errorMessage = "Método HTTP no admitido: " + ex.getMethod();
        return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
