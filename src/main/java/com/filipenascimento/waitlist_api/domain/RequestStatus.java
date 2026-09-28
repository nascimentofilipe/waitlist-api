package com.filipenascimento.waitlist_api.domain;

public enum RequestStatus {

    REQUESTED(false),
    IN_TRIAGE(false),
    REGULATED(false),
    AUTHORIZED(false),
    SCHEDULED(false),
    PERFORMED(true),
    CANCELED(true),
    RETURNED(false);

    private final boolean isFinal;

    RequestStatus(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
