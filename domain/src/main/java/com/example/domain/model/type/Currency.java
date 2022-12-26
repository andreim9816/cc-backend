package com.example.domain.model.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Currency {
    EUR("EUR"),
    USD("USD"),
    RON("RON");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }

    void setValue(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Currency getCurrenctByName(String value) {
        for (Currency currency : Currency.values()) {
            if (currency.getValue().equals(value)) {
                return currency;
            }
        }
        return null;
    }
}
