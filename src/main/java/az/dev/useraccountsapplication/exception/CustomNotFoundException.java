package az.dev.useraccountsapplication.exception;

import az.dev.useraccountsapplication.enums.ErrorCodeEnum;

public class CustomNotFoundException extends RuntimeException {

    private final int code;
    private final String message;


    public CustomNotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.message = errorCodeEnum.getMessage();
        this.code = errorCodeEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
