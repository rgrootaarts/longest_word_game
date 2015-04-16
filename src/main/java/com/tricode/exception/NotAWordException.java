package com.tricode.exception;

public class NotAWordException extends Throwable {

    public NotAWordException () {
        super("This word contains invalid characters");
    }
}
