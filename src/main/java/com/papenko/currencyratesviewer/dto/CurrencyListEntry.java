package com.papenko.currencyratesviewer.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Value
public class CurrencyListEntry implements Serializable {
    private static final long serialVersionUID = -6113383468342024698L;
    LocalDate date;
    List<CurrencyRate> rate;

    public CurrencyListEntry(LocalDate date, List<CurrencyRate> rate) {
        this.date = date;
        this.rate = List.copyOf(rate);
    }

    public List<CurrencyRate> getRate() {
        return List.copyOf(rate);
    }
}
