package com.astroganit.api.exception;

import com.astroganit.api.util.ResultCode;

public class SubscriptionNotActiveException extends AppException {

    public SubscriptionNotActiveException(String message) {
        super(ResultCode.SUBSCRIPTION_NOT_FOUND );
    }
}