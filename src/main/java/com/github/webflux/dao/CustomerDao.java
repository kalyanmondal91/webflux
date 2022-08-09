package com.github.webflux.dao;

import com.github.webflux.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class CustomerDao {

    public List<Customer> getCustomers()  {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> log.info("Processing Count : {}", i))
                .mapToObj(i -> new Customer(i, "Customer" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> log.info("Processing Count in stream flow : {}", i))
                .map(i -> new Customer(i, "Customer" + i));
    }

    public Flux<Customer> getCustomerList()  {
        return Flux.range(1,50)
                .doOnNext(i -> log.info("Processing Count in stream flow : {}", i))
                .map(i -> new Customer(i, "Customer" + i));
    }


    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
