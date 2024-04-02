package com.example.userservice_3.repositories;

import com.example.userservice_3.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{

    Token save(Token token);

    Optional<Token> findByValue(String tokenValue);

    Optional<Token> findByValueAndDeleted(String tokenValue, Boolean deleted);

    Optional<Token> findByValueAndDeletedAndExpiryDateGreaterThan(String token, Boolean deleted, Date date);





}
