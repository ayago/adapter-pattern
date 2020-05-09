package hexagonal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestControllerAdvices {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleBeanValidationException(ConstraintViolationException e){
        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .setMessage("Cannot process request with field violations")
                .setViolations(e.getConstraintViolations())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
