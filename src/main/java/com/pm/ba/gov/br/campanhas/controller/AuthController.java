package com.pm.ba.gov.br.campanhas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.pm.ba.gov.br.campanhas.config.CustomUserDetailsService;
import com.pm.ba.gov.br.campanhas.config.jwt.JWTUtil;
import com.pm.ba.gov.br.campanhas.dto.ApiResponse;
import com.pm.ba.gov.br.campanhas.dto.JwtAuthenticationResponse;
import com.pm.ba.gov.br.campanhas.dto.LoginRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/generatetoken")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        log.debug("Tentativa de autenticação para usuário: {}", loginRequest.getUsername());

        try {
            authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            log.debug("Autenticação bem-sucedida para usuário: {}", loginRequest.getUsername());
            
            if(loginRequest.getUsername() == null) {
                log.error("Username é nulo após autenticação bem-sucedida");
                return new ResponseEntity<>(new ApiResponse(false, "Username or password is invalid"),
                        HttpStatus.BAD_REQUEST);
            }
            
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
            log.debug("UserDetails carregado para usuário: {}", loginRequest.getUsername());
            
            String jwt = jwtUtil.generateToken(userDetails);
            log.debug("Token JWT gerado para usuário: {}", loginRequest.getUsername());
            
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (Exception e) {
            log.error("Erro durante autenticação: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    @GetMapping("/Hi")
    public String Hello() {
        return "Hello";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            log.debug("Iniciando autenticação para usuário: {}", username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.debug("Autenticação concluída com sucesso para usuário: {}", username);
        } catch (DisabledException e) {
            log.error("Usuário desabilitado: {}", username);
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.error("Credenciais inválidas para usuário: {}", username);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}