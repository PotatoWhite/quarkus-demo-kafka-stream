package me.potato.demo;

import io.smallrye.mutiny.Multi;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

@Slf4j
@ApplicationScoped
public class PriceGenerator {

  private Random random = new Random();

  @Outgoing("out-price")
  public Multi<Integer> generate() {
    return Multi.createFrom()
                .ticks()
                .every(Duration.ofSeconds(5))
                .onOverflow()
                .drop()
                .map(tick -> {
                  log.info("tick");
                  return random.nextInt(100);
                });
  }

}
