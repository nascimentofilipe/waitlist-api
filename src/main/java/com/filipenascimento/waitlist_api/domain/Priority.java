package com.filipenascimento.waitlist_api.domain;

public enum Priority {
    A1(14),
    A2(30),
    B(60),
    C(180),
    D(360);

    private static final double REFERENCE_DAYS = D.maxWaitDays;
    private final int maxWaitDays;

    Priority(int maxWaitDays) {
        this.maxWaitDays = maxWaitDays;
    }

    public int getMaxWaitDays() {
        return maxWaitDays;
    }

    public double getCoefficient() {
        return REFERENCE_DAYS / this.getMaxWaitDays();
    }

    public boolean bypassesScoring() {
        return this == A1;
    }
}
