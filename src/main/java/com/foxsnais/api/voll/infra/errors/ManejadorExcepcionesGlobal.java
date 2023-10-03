package com.foxsnais.api.voll.infra.errors;

import com.foxsnais.api.voll.model.ErrorInfo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

}
