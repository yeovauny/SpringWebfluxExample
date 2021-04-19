package com.example.ms.example.ms.example.controller;

import com.example.ms.example.ms.example.domain.ProductDTO;
import com.example.ms.example.ms.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class ProductController {

        @Autowired
        ProductService productService;

        @GetMapping(value = "/productoflux")
        public Flux<ServerSentEvent<ProductDTO>> productList(){
            return Flux.merge(productService.getProducts(),productService.getProducts3(),productService.getProducts4(),productService.getProducts5());
        }

        @GetMapping(value = "/productoflux2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
        public Flux<ProductDTO> productList2(){
                return productService.getProducts2();
        }


        @GetMapping(value = "/productomono", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
        public Mono<ProductDTO>  productMono(){

                return Mono.just(new ProductDTO(99999,"controles","se verifica el mono")).log();
        }

}
