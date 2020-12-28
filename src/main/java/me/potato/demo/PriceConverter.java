package me.potato.demo;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class PriceConverter {

  private static final double CONVERSION_RATE = 0.88;

  // Consume from the `prices` channel and produce to the `my-data-stream` channel
  @Incoming("in-price")
  @Outgoing("out-converted-event")
  @Broadcast
  @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
  public double process(int priceInUsd) {
    log.info("in-price {}", priceInUsd);
    return priceInUsd*CONVERSION_RATE;
  }

}
