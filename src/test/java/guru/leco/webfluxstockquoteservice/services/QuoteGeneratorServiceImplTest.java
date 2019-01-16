package guru.leco.webfluxstockquoteservice.services;

import guru.leco.webfluxstockquoteservice.model.Quote;
import guru.leco.webfluxstockquoteservice.services.contracts.QuoteGeneratorService;
import guru.leco.webfluxstockquoteservice.services.implementation.QuoteGeneratorServiceImpl;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class QuoteGeneratorServiceImplTest {

    private QuoteGeneratorService quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @Test
    public void fetchQuoteStream() throws Exception {

        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100l));


        quoteFlux.take(22000)
                .subscribe(System.out::println);

        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);
        Thread.sleep(1000);
    }

    @Test
    public void fetchQuoteDuration() throws Exception {

        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100l));

        Consumer<Quote> println = System.out::println;

        Consumer<Throwable> errorHandler = e -> System.out.println("Error during process Quote");

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable allDone = countDownLatch::countDown;

        quoteFlux.take(10)
                .subscribe(println, errorHandler, allDone);

        countDownLatch.wait();

    }

}