package com.papenko.currencyratesviewer.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class CurrencyRate implements Serializable {
    private static final long serialVersionUID = 1808170401637941294L;
    String currency;
    Double equivalent;
}
