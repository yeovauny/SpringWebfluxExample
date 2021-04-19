package com.example.bff.example.bff.example.controller;


import com.example.bff.example.bff.example.domain.ProductDTO;
import com.example.bff.example.bff.example.securityconfig.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
public class ProductController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private UserService userService;

    private static Logger logger = LogManager.getLogger(ProductController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @GetMapping(value = "/bffproducto", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ServerSentEvent<ProductDTO>> productList(){



        WebClient client = WebClient.create("http://localhost:8030");
        ParameterizedTypeReference<ServerSentEvent<ProductDTO>> type
                = new ParameterizedTypeReference<ServerSentEvent<ProductDTO>>() {};

        Flux<ServerSentEvent<ProductDTO>> eventStream = client.get()
                .uri("/productoflux")
                .retrieve()
                .bodyToFlux(type)
                .log();
                ;

        return eventStream;
    }

}
