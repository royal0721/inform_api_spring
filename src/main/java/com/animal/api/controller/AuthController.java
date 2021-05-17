package com.animal.api.controller;

import com.animal.api.auth.AuthRequest;
import com.animal.api.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> generateToken(@RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request);
        Map<String, String> response = Collections.singletonMap("token", token);
        Map<String, Object> result_response = jwtService.parseToken(response.get("token"));
        result_response.put("token",response.get("token"));

        //get user info
        return ResponseEntity.ok(result_response);
    }

    @PostMapping("/parse")
    public ResponseEntity<Map<String, Object>> parseToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Map<String, Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}
