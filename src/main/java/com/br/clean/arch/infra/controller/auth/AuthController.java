package com.br.clean.arch.infra.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.clean.arch.application.usecases.customer.CreateCustomer;
import com.br.clean.arch.application.usecases.customer.dto.input.CreateCustomerCommand;
import com.br.clean.arch.application.usecases.password.AuthenticateUser;
import com.br.clean.arch.infra.security.token.AuthRequest;
import com.br.clean.arch.infra.security.token.AuthResponse;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController 
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class); 

    private final CreateCustomer createCustomerUseCase; 
    private final AuthenticateUser authenticateUserUseCase; 

    public AuthController(CreateCustomer createCustomerUseCase, AuthenticateUser authenticateUserUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/register/customer") 
    @ResponseStatus(HttpStatus.CREATED) 
    public ResponseEntity<AuthResponse> registerCustomer(@RequestBody @Valid CreateCustomerCommand dto) {
        logger.info("Tentativa de registro de novo cliente com e-mail: {}", dto.email().getEmail());
        createCustomerUseCase.createCustomer(dto);

        String token = authenticateUserUseCase.authenticate(dto.email().getEmail(), dto.password());

        logger.info("Cliente registrado e autenticado com sucesso: {}", dto.email().getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping("/login/customer") 
    public ResponseEntity<AuthResponse> loginCustomer(@RequestBody @Valid AuthRequest request) {
        logger.info("Tentativa de login para o e-mail: {}", request.email().getEmail());
        String token = authenticateUserUseCase.authenticate(request.email().getEmail(), request.password());

        logger.info("Login bem-sucedido para o e-mail: {}", request.email().getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
