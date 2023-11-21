package org.nba.wsbasket.api;


public class ResponseObject <T> {
    T object;
    String message;

    public ResponseObject(T object, String message) {
        this.object = object;
        this.message = message;
    }

    public ResponseObject() {
    }
}
