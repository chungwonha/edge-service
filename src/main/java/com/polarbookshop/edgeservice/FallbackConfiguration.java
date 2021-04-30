package com.polarbookshop.edgeservice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FallbackConfiguration {

    static Logger logger = LoggerFactory.getLogger(FallbackConfiguration.class);
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        System.out.println("RouterFunction*******************************");
        return RouterFunctions
                .route(RequestPredicates.GET("/catalog-fallback"),
                        this::handleGetFallback)
                .andRoute(RequestPredicates.POST("/catalog-fallback"),
                        this::handlePostFallback);
    }

    public Mono<ServerResponse> handleGetFallback(ServerRequest request) {
        logger.debug("handleGetFallback*******************************************");
        System.out.println("handleGetFallback*******************************************");
        return ServerResponse.ok().body(Mono.just("Fallback"), String.class);
    }

    public Mono<ServerResponse> handlePostFallback(ServerRequest request) {
        logger.debug("handlePostFallback*******************************************");
        System.out.println("handlePostFallback*******************************************");
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}