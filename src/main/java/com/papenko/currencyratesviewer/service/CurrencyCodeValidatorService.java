package com.papenko.currencyratesviewer.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Locale;

@Service
@Validated
public class CurrencyCodeValidatorService {
    private static final List<String> OPEN_EXCHANGE_FREE_CURRENCIES =
            List.of("EUR", "USD", "GBP", "NZD", "AUD", "JPY"); //todo read from db
    public static final String MUST_BE_3_LETTER_STRING = "currency code must be 3-letter string";

    void validateFreeCurrency(@Size(min = 3, max = 3, message = MUST_BE_3_LETTER_STRING) String currency) {
        if (!OPEN_EXCHANGE_FREE_CURRENCIES.contains(currency.toUpperCase(Locale.US))) {
            throw new ConstraintViolationException("currency " + currency
                    + " is not supported in free plan, use one of " + OPEN_EXCHANGE_FREE_CURRENCIES, null);
        }
    }
}
