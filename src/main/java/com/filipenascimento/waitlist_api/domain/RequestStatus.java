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

    public boolean canTransitionTo(RequestStatus target) {
        if (!isFinal() && target == CANCELED) {
            return true;
        }

        return switch (this) {
            case REQUESTED -> target == IN_TRIAGE || target == RETURNED;
            case IN_TRIAGE -> target == REGULATED || target == RETURNED;
            case REGULATED -> target == AUTHORIZED;
            case AUTHORIZED -> target == SCHEDULED;
            case SCHEDULED -> target == PERFORMED;
            case RETURNED -> target == REQUESTED;
            case PERFORMED, CANCELED -> false;
        };
    }
}
