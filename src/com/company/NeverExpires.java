package com.company;

public class NeverExpires implements ExpirationStrategy{
    @Override
    public boolean isExpired() {
        return false;
    }
}
