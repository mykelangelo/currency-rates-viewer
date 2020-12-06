package com.papenko.currencyratesviewer.controller;

import com.papenko.currencyratesviewer.dto.CurrencyList;
import com.papenko.currencyratesviewer.dto.ErrorMessage;
import com.papenko.currencyratesviewer.service.RatesViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;


@RestController
@RequestMapping("rates")
@RequiredArgsConstructor
public class RatesViewerController {
    private final RatesViewerService service;

    @GetMapping
    public String info() {
        return "Go to /rates/{currency} to see the list of currency rates for the last 10 days";
    }

    @GetMapping("{currency}")
    public CurrencyList getCurrencyRates(@PathVariable String currency) {
        return service.getCurrencyRates(currency);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
    }
}
