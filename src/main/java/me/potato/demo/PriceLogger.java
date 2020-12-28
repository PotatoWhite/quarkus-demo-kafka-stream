package me.potato.demo;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class PriceLogger {

  @Incoming("out-converted-event")
  public void logging(Double price) {
    log.info("out-converted-event : {}", price);
  }
}
