package guru.leco.webfluxstockquoteservice.services.contracts;

import guru.leco.webfluxstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period);
}
