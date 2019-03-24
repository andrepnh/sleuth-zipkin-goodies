package com.github.andrepnh.tracing.order;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {
  @Bean
  public HttpClient httpClient(
      @Value("${read.timeout.ms}") int readTimeoutMs,
      @Value("${use.service.based.urls}") boolean serviceBasedUrls,
      RestTemplateBuilder builder) {
    var restTemplate = builder.setReadTimeout(Duration.ofMillis(readTimeoutMs)).build();
    var urlSupplier = serviceBasedUrls ? new ServiceBasedUrlSupplier() : new LocalhostUrlSupplier();
    return new HttpClient(restTemplate, urlSupplier);
  }
}
