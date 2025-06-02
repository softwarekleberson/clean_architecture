package com.br.clean.arch.infra.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.clean.arch.domain.entitie.address.exception.AddressNotFoundException;
import com.br.clean.arch.domain.entitie.address.exception.IncorrectCepException;
import com.br.clean.arch.domain.entitie.card.exeptions.DuplicateCardException;
import com.br.clean.arch.domain.entitie.card.exeptions.CardNotFoundException;
import com.br.clean.arch.domain.entitie.customer.exceptions.CustomerNotFoundException;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.DuplicateEmailException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorrectCpfException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretEmailException;
import com.br.clean.arch.domain.entitie.customer.exceptions.IncorretPhoneException;
import com.br.clean.arch.domain.entitie.customer.exceptions.UnderageException;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestControllerAdvice 
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class); // Logger para registrar eventos e erros.

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handlerError404(EntityNotFoundException ex) {
        logger.warn("Recurso não encontrado: {}", ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorResponse>> handlerError400(MethodArgumentNotValidException ex) {
        logger.warn("Erro de validação de argumento: {}", ex.getMessage());
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorResponse::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handlerError400(HttpMessageNotReadableException ex) {
        logger.warn("Corpo da requisição ilegível: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Requisição inválida", ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handlerErrorBadCredentials(BadCredentialsException ex) {
        logger.warn("Tentativa de login com credenciais inválidas: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Autenticação falhou", "Credenciais inválidas."));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handlerErrorAccessDenied(AccessDeniedException ex) {
        logger.warn("Acesso negado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Acesso negado", "Você não tem permissão para acessar este recurso."));
    }
    
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> handlerAuthenticationFailed(AuthenticationFailedException ex) {
        logger.warn("Falha na autenticação: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Autenticação falhou", ex.getMessage()));
    }

    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<ErrorResponse> handlerDuplicateCustomer(DuplicateCustomerException ex) {
        logger.warn("Tentativa de registro de cliente duplicado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), "Conflito de dados", ex.getMessage()));
    }

    @ExceptionHandler({
            AddressNotFoundException.class,
            IncorrectCepException.class,
            CustomerNotFoundException.class,
            CardNotFoundException.class,
            IncorrectCpfException.class,
            IncorretEmailException.class,
            IncorretPhoneException.class,
            UnderageException.class
    })
    public ResponseEntity<ErrorResponse> handlerBusinessRuleBadRequest(RuntimeException ex) {
        logger.warn("Erro de regra de negócio (Bad Request): {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação de negócio", ex.getMessage()));
    }

    @ExceptionHandler({
            DuplicateCardException.class,
            DuplicateCpfException.class,
            DuplicateEmailException.class
    })
    public ResponseEntity<ErrorResponse> handlerBusinessRuleConflict(RuntimeException ex) {
        logger.warn("Erro de regra de negócio (Conflito): {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(HttpStatus.CONFLICT.value(), "Conflito de dados", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerError500(Exception ex) {
        logger.error("Erro interno do servidor: {}", ex.getMessage(), ex); // Loga a stack trace para erros 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno do servidor", "Ocorreu um erro inesperado. Tente novamente mais tarde."));
    }

    private record ValidationErrorResponse(String field, String message, LocalDateTime timestamp) {
        public ValidationErrorResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage(), LocalDateTime.now());
        }
    }

    private record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
        public ErrorResponse(int status, String error, String message) {
            this(status, error, message, LocalDateTime.now());
        }
    }
}
