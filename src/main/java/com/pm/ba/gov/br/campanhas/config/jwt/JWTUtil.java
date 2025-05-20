package com.pm.ba.gov.br.campanhas.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    @Value("${spring.app.jwtSecret}")
    private String SECRET_KEY;

    @Value("${spring.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    private byte[] getSigningKey() {
        try {
            byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            logger.debug("Usando chave de assinatura: {}", SECRET_KEY);
            return keyBytes;
        } catch (Exception e) {
            logger.error("Erro ao gerar chave de assinatura: {}", e.getMessage());
            throw e;
        }
    }

    public String extractUsername(String token) {
        try {
            String username = extractClaim(token, Claims::getSubject);
            logger.debug("Username extraído do token: {}", username);
            return username;
        } catch (Exception e) {
            logger.error("Erro ao extrair username do token: {}", e.getMessage());
            throw e;
        }
    }

    public Date extractExpiration(String token) {
        try {
            Date expiration = extractClaim(token, Claims::getExpiration);
            logger.debug("Data de expiração extraída do token: {}", expiration);
            return expiration;
        } catch (Exception e) {
            logger.error("Erro ao extrair data de expiração do token: {}", e.getMessage());
            throw e;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = extractAllClaims(token);
            T result = claimsResolver.apply(claims);
            logger.debug("Claim extraído do token: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Erro ao extrair claim do token: {}", e.getMessage());
            throw e;
        }
    }

    private Claims extractAllClaims(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            logger.debug("Claims extraídos do token: {}", claims);
            return claims;
        } catch (Exception e) {
            logger.error("Erro ao extrair claims do token: {}", e.getMessage());
            throw e;
        }
    }

    private Boolean isTokenExpired(String token) {
        try {
            Boolean expired = extractExpiration(token).before(new Date());
            logger.debug("Token expirado: {}", expired);
            return expired;
        } catch (Exception e) {
            logger.error("Erro ao verificar expiração do token: {}", e.getMessage());
            throw e;
        }
    }

    public String generateToken(UserDetails userDetails) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", userDetails.getAuthorities());
            claims.put("iss", "http://localhost:8080");
            
            String token = createToken(claims, userDetails.getUsername());
            logger.debug("Token gerado para usuário: {}", userDetails.getUsername());
            return token;
        } catch (Exception e) {
            logger.error("Erro ao gerar token: {}", e.getMessage());
            throw e;
        }
    }

    private String createToken(Map<String, Object> claims, String subject) {
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS256, getSigningKey())
                    .compact();
            logger.debug("Token criado para subject: {}", subject);
            return token;
        } catch (Exception e) {
            logger.error("Erro ao criar token: {}", e.getMessage());
            throw e;
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            Boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
            logger.debug("Token válido para usuário {}: {}", username, isValid);
            return isValid;
        } catch (Exception e) {
            logger.error("Erro ao validar token: {}", e.getMessage());
            return false;
        }
    }
}
