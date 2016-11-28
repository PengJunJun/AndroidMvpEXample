package com.hankou.utils.network;

/**
 * Created by bykj003 on 2016/8/15.
 */
public class ApiException extends RuntimeException {

    private int mErrorCode;
    private String mErrorMessage;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMessage;
    }
}
