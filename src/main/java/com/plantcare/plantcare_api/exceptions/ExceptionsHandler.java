package com.plantcare.plantcare_api.exceptions;

import com.plantcare.plantcare_api.DTOs.response.RestErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>ValidationError(MethodArgumentNotValidException exception, HttpServletRequest request){
        FieldError error = exception.getBindingResult().getFieldErrors().getFirst();

        RestErrorDTO dto = new RestErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                error.getDefaultMessage()
        );
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(SenhaException.class)
    public ResponseEntity<?>SenhaError(SenhaException exception){
        RestErrorDTO dto = new RestErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<?>emailJaExiste(EmailException exception){
        RestErrorDTO dto = new RestErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>GenericError(Exception exception){
        RestErrorDTO dto = new RestErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Erro interno do Servidor"
        );
        return ResponseEntity.internalServerError().body(dto);
    }
}
