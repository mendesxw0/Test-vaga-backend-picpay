package com.example.com.picPaySImplificado.infra;

import com.example.com.picPaySImplificado.dto.ExeptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExeptionHendler {

    // Cadastro de dois users com mesmo documento.
    // tratamento de exceções do JPA
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity theatDuplicationEntry(DataIntegrityViolationException exception) {
        ExeptionDTO exeptionDTO = new ExeptionDTO("Usuario ja cadastrado. ", "400");
        return ResponseEntity.badRequest().body(exeptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException exception) {
        ExeptionDTO exeptionDTO = new ExeptionDTO("Usuario ja cadastrado. ", "400");
        return ResponseEntity.notFound().build();
    }

    // Tratamento da Execeção geral:
    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGenerationExeption(Exception exception) {
        ExeptionDTO exeptionDTO = new ExeptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exeptionDTO);
    }

}