package com.papenko.currencyratesviewer.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rate-providers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RateProvidersProperties {
    OpenExchangeProperties openExchange;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OpenExchangeProperties {
        String applicationId;
        String baseUrl;
    }
}

