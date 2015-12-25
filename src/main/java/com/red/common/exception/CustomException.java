package com.red.common.exception;

/**
 * Created by Star on 2015/12/25.
 */
public class CustomException extends Exception implements BasicException {
    private int code = 0;

    /**
     * Instantiates a new Custom exception.
     *
     * @param message the message
     * @param code    the code
     */
    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    @Override
    public int getErrorCode() {
        return code;
    }
}
