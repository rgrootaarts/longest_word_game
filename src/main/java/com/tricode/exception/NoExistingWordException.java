package com.tricode.exception;

public class NoExistingWordException extends Exception {

    public NoExistingWordException () {
        super("This word does not exist");
    }
}
