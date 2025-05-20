package com.pm.ba.gov.br.campanhas.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilterS extends OncePerRequestFilter {
 
    private static Logger log = LogManager.getLogger(JwtRequestFilterS.class);
 
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        log.debug("Processando requisição para: {}", request.getRequestURI());
        
        // Permite acesso ao endpoint de autenticação sem token
        if (request.getRequestURI().equals("/api/auth/generatetoken")) {
            log.debug("Endpoint de autenticação detectado, permitindo acesso sem token");
            chain.doFilter(request, response);
            return;
        }
        
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            log.debug("Token JWT recebido: {}", jwtToken);
            
            try {
                username = jwtUtil.extractUsername(jwtToken);
                log.debug("Username extraído do token: {}", username);
            } catch (IllegalArgumentException e) {
                log.error("Não foi possível obter o token JWT: {}", e.getMessage());
            } catch (ExpiredJwtException e) {
                log.error("Token JWT expirado: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token expirado");
                return;
            }
        } else {
            log.warn("Token JWT não encontrado ou formato inválido");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("Carregando UserDetails para username: {}", username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                log.debug("Token JWT válido para usuário: {}", username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.debug("Autenticação configurada com sucesso para usuário: {}", username);
            } else {
                log.error("Token JWT inválido para usuário: {}", username);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
