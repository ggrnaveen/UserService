package com.example.userservice_3.services;


import com.example.userservice_3.models.Token;
import com.example.userservice_3.models.User;
import com.example.userservice_3.repositories.TokenRepository;
import com.example.userservice_3.repositories.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

          public   UserService(UserRepository userRepository, TokenRepository tokenRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
                this.userRepository = userRepository;
                this.bCryptPasswordEncoder=bCryptPasswordEncoder;
                this.tokenRepository=tokenRepository;
            }
        public User signUp(String name, String email, String password){

            User newUser= new User();

            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setHashedPassWord(bCryptPasswordEncoder.encode(password));

           return userRepository.save(newUser);


        }

        public Token login(String email, String password){


            Optional<User> userOptional = userRepository.findByEmail(email);



            if(userOptional.isEmpty()){
                return null;
            }
            User user = userOptional.get();





            if(!bCryptPasswordEncoder.matches(password, user.getHashedPassWord())){
                return null;
            }

            System.out.println(
                    userOptional.get().getName()
            );

            Token token = new Token();

            token.setUser(user);

            LocalDate now = LocalDate.now(); // today's date
            LocalDate expiryDate = now.plus(Period.ofDays(30)); // add 30

            Date date = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());



            token.setExpiryDate(date);


            token.setValue(RandomStringUtils.randomAlphanumeric(128));

            Token savedToken = tokenRepository.save(token);

            return savedToken;



        }


        public void logOut(String tokenValue){
            Optional<Token> tokenOptional = tokenRepository.findByValueAndDeleted(tokenValue, false);
            if(tokenOptional.isEmpty()){
                return;
            }
            Token token = tokenOptional.get();
            token.setDeleted(true);

            tokenRepository.save(token);
        }


        public User validateToken(String tokenValue){

            Optional<Token> tokenOptional =
                    tokenRepository.findByValueAndDeletedAndExpiryDateGreaterThan(tokenValue, false, new Date());
            if(tokenOptional.isEmpty()){
                return null;
            }


            Token token = tokenOptional.get();
            return token.getUser();
        }

}
