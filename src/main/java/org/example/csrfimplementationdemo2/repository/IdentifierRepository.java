package org.example.csrfimplementationdemo2.repository;

import org.example.csrfimplementationdemo2.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Optional;

public interface IdentifierRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByIdentifier(String identifier);
}
