package az.dev.useraccountsapplication.dto.response;

import lombok.Builder;

import java.io.Serializable;


@Builder
public class ErrorResponse implements Serializable {
    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ErrorResponse() {

    }

    public ErrorResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static ErrorResponse getSuccessMessage() {
        return new ErrorResponse("Success", 1);
    }



}
