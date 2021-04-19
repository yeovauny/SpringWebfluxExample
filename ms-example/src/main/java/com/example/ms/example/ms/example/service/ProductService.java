package com.example.ms.example.ms.example.service;

import com.example.ms.example.ms.example.domain.ProductDTO;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.Duration;

@Service
public class ProductService {

    public Flux<ServerSentEvent<ProductDTO>> getProducts(){

        return Flux.just(ServerSentEvent.<ProductDTO> builder()
                .id(String.valueOf(1))
                .event("FLUX 1")
                .data(new ProductDTO(1000,"credito juegos FLUX1 ","10000000"))
                .build()).delaySequence(Duration.ofSeconds(5));

    }

    // before to do ServerSentEvent to object Flux
    public Flux<ProductDTO> getProducts2(){

        return Flux.just(new ProductDTO(7000,"credito de consumo FLUX2"," por 1000 pesos"),new ProductDTO(5000,"credito linea blanca FLUX2","por 50000000 pesos"),new ProductDTO(600,"credito sala FLUX2","por 10000000 pesos"))
                .delayElements(Duration.ofSeconds(8))
                .log();

    }


    public Flux<ServerSentEvent<ProductDTO>> getProducts3() {

        return Flux.just(ServerSentEvent.<ProductDTO>builder()
                .id(String.valueOf(1))
                .event("FLUX 2")
                .data(new ProductDTO(1000, "credito juegos FLUX2 ", "10000000"))
                .build()).delaySequence(Duration.ofSeconds(6));
    }

    public Flux<ServerSentEvent<ProductDTO>> getProducts5() {

        return Flux.just(ServerSentEvent.<ProductDTO>builder()
                .id(String.valueOf(1))
                .event("FLUX 3")
                .data(new ProductDTO(1000, "credito juegos FLUX3 ", "10000000"))
                .build()).delaySequence(Duration.ofSeconds(9));
    }

    public Flux<ServerSentEvent<ProductDTO>> getProducts4() {

        return Flux.just(ServerSentEvent.<ProductDTO>builder()
                .id(String.valueOf(1))
                .event("FLUX 4")
                .data(new ProductDTO(1000, "credito juegos FLUx4 ", "10000000"))
                .build()).delaySequence(Duration.ofSeconds(3));
    }


}
