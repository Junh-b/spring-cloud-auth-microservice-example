package net.junhabaek.authservice.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;


    private ErrorResponse(final ErrorStatus status, final List<FieldError> errors) {
        this.message = status.getMessage();
        this.status = status.getStatus();
        this.errors = errors;
        this.code = status.getCode();
    }

    private ErrorResponse(final ErrorStatus status) {
        this.message = status.getMessage();
        this.status = status.getStatus();
        this.code = status.getCode();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(final ErrorStatus status, final BindingResult bindingResult) {
        return new ErrorResponse(status, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final ErrorStatus status) {
        return new ErrorResponse(status);
    }

    public static ErrorResponse of(final ErrorStatus status, final List<FieldError> errors) {
        return new ErrorResponse(status, errors);
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorStatus.INVALID_TYPE_VALUE, errors);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

}
