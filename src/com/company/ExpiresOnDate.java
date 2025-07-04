package com.company;

import java.time.LocalDate;
public class ExpiresOnDate implements ExpirationStrategy{
    private final LocalDate expiryDate;

    public ExpiresOnDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
