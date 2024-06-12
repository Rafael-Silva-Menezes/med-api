package med.vol.api.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrors {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerEntityNotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValidError(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity handleValidateException(ValidateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DataValidationError(String field, String message) {
        public DataValidationError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}


 /* @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handlerDuplicateKeyError(DataIntegrityViolationException ex) {
        String errorMessage = ex.getMessage();
        String fieldName = "";
        if (errorMessage.contains("email")) {
            fieldName = "email";
        } else if (errorMessage.contains("crm")) {
            fieldName = "crm";
        } else if (errorMessage.contains("phone")) {
            fieldName = "phone";
        } else if (errorMessage.contains("phone")) {
            fieldName = "document";
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDetail("Duplicate data", fieldName + " already exist"));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handlerHttpMessageNotReadableError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handlerBadCredentialsError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials!");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handlerAuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handlerAccessDeniedError() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied!");

    private record ErrorDetail(String error, String message) {
    }*/