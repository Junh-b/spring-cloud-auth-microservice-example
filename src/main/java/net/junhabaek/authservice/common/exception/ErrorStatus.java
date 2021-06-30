package net.junhabaek.authservice.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorStatus {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Method"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access Denied")
    ;



    private final String code;
    private final String message;
    private int status;

    ErrorStatus(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}

