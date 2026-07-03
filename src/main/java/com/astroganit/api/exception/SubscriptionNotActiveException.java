package com.astroganit.api.exception;
public class SubscriptionNotActiveException extends AppException {

    public SubscriptionNotActiveException(String message) {
        super(ErrorCodes.SUBSCRIPTION_NOT_ACTIVE, message);
    }
}