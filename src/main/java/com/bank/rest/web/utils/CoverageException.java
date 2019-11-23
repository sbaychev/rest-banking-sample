package com.bank.rest.web.utils;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public class CoverageException extends Exception {

    /**
     * Instantiates a new Coverage exception.
     *
     * @param errorMessage the error message
     */
    public CoverageException(String errorMessage) {
        super(errorMessage);
    }
}
