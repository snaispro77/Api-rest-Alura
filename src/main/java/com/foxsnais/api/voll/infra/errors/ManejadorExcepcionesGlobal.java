package com.foxsnais.api.voll.infra.errors;

import com.foxsnais.api.voll.model.ErrorInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ManejadorExcepcionesGlobal extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarError404(){
        String mensaje = "No se encontró el medico con el ID Proporcionado";
        return ResponseEntity.internalServerError().body(new ErrorInfo(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), mensaje
        ));

    }
    @ExceptionHandler(ValidacionIntegridadException.class)
    public ResponseEntity<ErrorInfo> tratarError404(ValidacionIntegridadException ex){
        return ResponseEntity.internalServerError().body(new ErrorInfo(
                HttpStatus.NOT_FOUND.value(), ex.getMessage()
        ));
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorInfo> tratarErrorFormato(ValidationException ex){
        return ResponseEntity.badRequest().body(new ErrorInfo(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage()
        ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String nombresErrores = ex.getFieldErrors().stream().map(fieldError -> fieldError.getField())
                .collect(Collectors.joining(","));

        return this.handleExceptionInternal(ex,new ErrorInfo(HttpStatus.BAD_REQUEST.value(),
                "Formato invalido en el campo " + nombresErrores), headers, status, request);
    }

}
