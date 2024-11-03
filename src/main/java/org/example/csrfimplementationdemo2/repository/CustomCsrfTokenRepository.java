package org.example.csrfimplementationdemo2.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.csrfimplementationdemo2.Entity.Token;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final IdentifierRepository identifierRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN","_csrf", uuid);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {

        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existToken = identifierRepository.findByIdentifier(identifier);

        if (existToken.isPresent()) {
            Token entityToken = existToken.get();
            entityToken.setToken(token.getToken());
        } else {
            Token entityToken = new Token();
            entityToken.setToken(token.getToken());
            entityToken.setIdentifier(identifier);
            identifierRepository.save(entityToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existToken = identifierRepository.findByIdentifier(identifier);

        if (existToken.isPresent()) {
            Token token = existToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN",
                    "_csrf",
                    token.getToken());
        }
        return null;
    }
}
