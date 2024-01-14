package com.example.fluxdemo.web;

import com.example.fluxdemo.domain.Customer;
import com.example.fluxdemo.domain.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
public class CustomerController {

      private final CustomerRepository customerRepository;
      private final Sinks.Many<Customer> sink;

      // A 요청 -> Flux -> Stream
      // B 요청 -> Flux -> Stream
      // sink는 모든 클라이언트의 플럭스 요청을 머지한다.

      public CustomerController(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
            this.sink = Sinks.many().multicast().onBackpressureBuffer();
      }

      @GetMapping("/customer")
      public Flux<Customer> findAll() {
            return customerRepository.findAll().log();
      }

      // onNext 되면 한번에 모았다가 플러쉬
      @GetMapping("/flux")
      public Flux<Integer> flux() {
            return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
      }


      // response가 유지되며 하나씩 받기
      @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
      public Flux<Integer> fluxstream() {
            return Flux.just(1,2,3,4,5).delayElements(Duration.ofSeconds(1)).log();
      }


      // 줄게 하나밖에 없으니까 Flux가 아닌 Mono로 리턴
      @GetMapping("/customer/{id}")
      public Mono<Customer> findById(@PathVariable Long id) {
            return customerRepository.findById(id).log();
      }

      @GetMapping(value = "/customer/sse/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // SSE 프로토콜 적용됨
      public Flux<Customer> findAllSSEtest() {
            return customerRepository.findAll().delayElements(Duration.ofSeconds(1)).log(); // 다 던지고 멈춤
      }

      @GetMapping(value = "/customer/sse") // produces 생략 가능
      public Flux<ServerSentEvent<Customer>> findAllSSE() {
            return sink.asFlux().map(c -> ServerSentEvent.builder(c).build()).doOnCancel(()-> {
                  sink.asFlux().blockLast(); // onComplete 호출
            });
      }

      @PostMapping("/customer")
      public Mono<Customer> save() {
            return customerRepository.save(new Customer("gildong", "Hong")).doOnNext(c -> {
                  sink.tryEmitNext(c);
            });
      }
}
