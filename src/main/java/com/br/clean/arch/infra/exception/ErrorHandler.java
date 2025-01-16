package com.br.clean.arch.infra.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.clean.arch.domain.entitie.address.exception.IncorrectAddressException;
import com.br.clean.arch.domain.entitie.address.exception.IncorrectCepException;
import com.br.clean.arch.domain.entitie.card.exeptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.card.exeptions.DuplicateCardException;
import com.br.clean.arch.domain.entitie.card.exeptions.IncorrectCardExpetion;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateEmailException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorrectCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretEmailException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretPhoneException;
import com.br.clean.arch.domain.entitie.customer.exceptions.UnderageException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerError400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DateErrorValidat::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handlerError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handlerErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handlerErrorAcessDenided() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acess denied");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }
    
    /*Handler busines rule*/
    @ExceptionHandler(IncorrectAddressException.class)
    public ResponseEntity handlerErrorBusinesRule(IncorrectAddressException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectCepException.class)
    public ResponseEntity handlerErrorBusinesRule(IncorrectCepException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity handlerErrorBusinesRule(CustomerNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateCardException.class)
    public ResponseEntity handlerErrorBusinesRule(DuplicateCardException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(IncorrectCardExpetion.class)
    public ResponseEntity handlerErrorBusinesRule(IncorrectCardExpetion ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(DuplicateCpfException.class)
    public ResponseEntity handlerErrorBusinesRule(DuplicateCpfException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(IncorrectCpfException.class)
    public ResponseEntity handlerErrorBusinesRule(IncorrectCpfException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity handlerErrorBusinesRule(DuplicateEmailException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(IncorretEmailException.class)
    public ResponseEntity handlerErrorBusinesRule(IncorretEmailException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(IncorretPhoneException.class)
    public ResponseEntity handlerErrorBusinesRule(IncorretPhoneException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(UnderageException.class)
    public ResponseEntity handlerErrorBusinesRule(UnderageException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    /*Handler busines rule*/
    
    private record DateErrorValidat(String fild, String message) {
        public DateErrorValidat(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
