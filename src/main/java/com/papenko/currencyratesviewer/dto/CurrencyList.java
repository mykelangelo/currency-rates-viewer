package com.papenko.currencyratesviewer.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class CurrencyList implements Serializable {
    private static final long serialVersionUID = -7408746439720513835L;
    List<CurrencyListEntry> currencies;

    public CurrencyList(List<CurrencyListEntry> currencies) {
        this.currencies = List.copyOf(currencies);
    }

    public List<CurrencyListEntry> getCurrencies() {
        return List.copyOf(currencies);
    }
}
