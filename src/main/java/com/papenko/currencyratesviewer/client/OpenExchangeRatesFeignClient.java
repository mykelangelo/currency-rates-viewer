package com.papenko.currencyratesviewer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Map;

@FeignClient(name = "open-exchange-rates", url = "#{@rateProvidersProperties.openExchange.baseUrl}")
public interface OpenExchangeRatesFeignClient {

    @GetMapping("/historical/{date}.json?app_id={appId}")
    Map<String, Object> getHistoricalRates(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                           @PathVariable String appId);
}