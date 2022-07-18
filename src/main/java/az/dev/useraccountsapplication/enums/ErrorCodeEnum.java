package az.dev.useraccountsapplication.enums;

public enum ErrorCodeEnum  {
    USER_NOT_FOUND(1001, "User not found"),
    IS_EMPTY(1002,"is empty"),
    ACCOUNT_NOT_FOUND(1003,"Account not found");


    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
