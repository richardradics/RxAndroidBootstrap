package com.richardradics.core.network;

/**
 * Created by mark on 2015. 03. 30..
 */
public class NoConnectivityException extends RuntimeException {

    protected final String reason;


    public NoConnectivityException(String message) {
        super(message);
        this.reason = message;
    }

    @Override
    public String getMessage() {
        return reason;
    }

    public String getReason() {
        return reason;
    }

}
