package com.tickets.requirement_sv.configuration.feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

        @Bean
        public ErrorDecoder errorDecoder() {
            return new CustomErrorDecoder();
        }
}
