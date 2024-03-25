package com.example.SongShareBackend.controllers;

import com.example.SongShareBackend.models.LoginRequest;
import com.example.SongShareBackend.models.SongshareUser;
import com.example.SongShareBackend.services.Userservice;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class Usercontroller {
    private final Userservice userService;

    @Autowired
    public Usercontroller(Userservice userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<SongshareUser> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        SongshareUser user = userService.getUserByUsername(username);


        if (user != null && Objects.equals(user.getPassword(), password))
        {

            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String secret = Encoders.BASE64.encode(key.getEncoded());

            String token = Jwts.builder()
                    .setSubject(username)
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();

            return ResponseEntity.ok(token);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
