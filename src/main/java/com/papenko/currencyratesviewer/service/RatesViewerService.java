package com.papenko.currencyratesviewer.service;

import com.papenko.currencyratesviewer.dto.CurrencyList;
import com.papenko.currencyratesviewer.dto.CurrencyListEntry;
import com.papenko.currencyratesviewer.dto.CurrencyRate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RatesViewerService {
    public static final String BASE_CURRENCY = "USD";
    static List<String> usedCurrencies = List.of("EUR", "USD", "GBP", "NZD", "AUD", "JPY"); //todo read from db
    static int HOW_MANY_DAYS = 10; //todo read from properties
    RatesGetterService ratesGetterService;
    CurrencyCodeValidatorService currencyCodeValidatorService;

    @Cacheable(cacheNames = "final", key = "#currency")
    public CurrencyList getCurrencyRates(String currency) {
        currencyCodeValidatorService.validateFreeCurrency(currency);
        log.warn("Calculations will take place");
        val currencies = new ArrayList<CurrencyListEntry>(HOW_MANY_DAYS);
        for (int i = 0; i < HOW_MANY_DAYS; i++) {
            val date = LocalDate.now(ZoneId.of("UTC")).minusDays(i);
            val historicalRates = ratesGetterService.getHistoricalRates(date);

            List<CurrencyRate> currencyRates;
            if (currency.equalsIgnoreCase(BASE_CURRENCY)) {
                currencyRates = usedCurrencies.stream()
                        .map(cur -> new CurrencyRate(cur, toDouble(historicalRates.get(cur))))
                        .collect(Collectors.toList());
            } else {
                val baseEq = toDouble(historicalRates.get(currency.toUpperCase(Locale.US)));
                currencyRates = usedCurrencies.stream()
                        .map(cur -> new CurrencyRate(cur, calculateEq(baseEq, toDouble(historicalRates.get(cur)))))
                        .collect(Collectors.toList());
            }

            currencies.add(new CurrencyListEntry(date, currencyRates));
        }
        return new CurrencyList(currencies);
    }

    private static Double calculateEq(Double baseEquivalent, Double equivalent) {
        return equivalent / baseEquivalent;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(cacheNames = "final", allEntries = true)
    public void evictCache() {
        log.warn("Cache has been evicted");
    }

    private static Double toDouble(Object o) {
        return o.getClass() == Integer.class ? (double) (Integer) o : (Double) o;
    }
}
