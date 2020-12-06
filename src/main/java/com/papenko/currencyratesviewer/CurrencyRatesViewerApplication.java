package com.papenko.currencyratesviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class CurrencyRatesViewerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyRatesViewerApplication.class, args);
    }
}
