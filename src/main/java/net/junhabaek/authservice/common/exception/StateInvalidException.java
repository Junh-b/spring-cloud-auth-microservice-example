package net.junhabaek.authservice.common.exception;

public class StateInvalidException extends BusinessException{
    public StateInvalidException(String message) {
        super(message, ErrorStatus.INVALID_TYPE_VALUE);
    }
}

