package com.papenko.currencyratesviewer.service;

import com.papenko.currencyratesviewer.client.OpenExchangeRatesFeignClient;
import com.papenko.currencyratesviewer.properties.RateProvidersProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatesGetterService {
    public static final String OPEN_EXCHANGE_RATES = "rates";

    RateProvidersProperties rateProvidersProperties;
    OpenExchangeRatesFeignClient client;

    @Cacheable(cacheNames = "historical", key = "#date")
    public Map<String, Object> getHistoricalRates(LocalDate date) {
        log.warn("New read from an API");
        val appId = rateProvidersProperties.getOpenExchange().getApplicationId();
        return (Map<String, Object>) client.getHistoricalRates(date, appId).get(OPEN_EXCHANGE_RATES);
    }
}
