package com.github.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    @Test
    public void monoTest() {
        Mono<String> monoString = Mono.just("Mono Techie").log();
        monoString.subscribe(System.out::println);
    }

    @Test
    public void fluxTest() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Microservice")
                .concatWithValues("Aws", "Azure")
                .log();
        fluxString.subscribe(System.out::println);
    }

    @Test
    public void monoTestError() {
        Mono<?> monoString = Mono.just("Mono Techie")
                .then(Mono.error(new RuntimeException("Exception")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void fluxTestError() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Microservice")
                .concatWithValues("Aws", "Azure")
                .concatWith(Flux.error(new RuntimeException("Exception")))
                .concatWithValues("Cloud")
                .log();
        fluxString.subscribe(System.out::println);
    }
}
