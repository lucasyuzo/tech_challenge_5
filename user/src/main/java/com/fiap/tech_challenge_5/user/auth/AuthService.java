package com.fiap.tech_challenge_5.user.auth;

import com.fiap.tech_challenge_5.user.user.User;
import com.fiap.tech_challenge_5.user.user.UserRepository;
import com.fiap.tech_challenge_5.user.user.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Boolean validateCredentials(LoginDTO loginDTO, User user) {
        return loginDTO.login().equals(user.getLogin()) && loginDTO.password().equals(user.getPassword());
    }

    public String generateToken(UUID userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token válido por 10 horas
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token, UUID userId) {
        String tokenUserId = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (userId.toString().equals(tokenUserId) && !isTokenExpired(token));
    }

    public boolean validateTokenForAdmin(String token, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        String tokenUserId = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (userId.toString().equals(tokenUserId) && !isTokenExpired(token) && user.getRole().equals(UserRole.ADMIN));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
