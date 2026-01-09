package com.project.ticketeria.modules.token.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.project.ticketeria.modules.userBasic.UserBasicEntity;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service
public class JwtUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateToken(UserBasicEntity user) {
        // TENHO QUE INSERIR ROLE NO TOKEN
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name());

        String token = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isValid(String token, UserDetails user) {
        try {
            String username = extractUsername(token);

            // se já existir no redis, significa que foi invalidado(Logout)
            boolean isBlackelisted = Boolean.TRUE.equals(redisTemplate.hasKey(token));

            if ((username.equals(user.getUsername())) && !isTokenExpired(token) && !isBlackelisted) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar no Redis: " + e.getMessage());
            return !isTokenExpired(token);
        }

    }

    public long getExpirationTime(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.getTime() - System.currentTimeMillis();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    @SuppressWarnings("null")
    public void addTokenToBlacklist(String token) {
        long expirationTime = getExpirationTime(token);
        if (expirationTime > 0) {
            redisTemplate.opsForValue().set(token, "blacklisted", java.time.Duration.ofMillis(expirationTime));
        }

    }
}
