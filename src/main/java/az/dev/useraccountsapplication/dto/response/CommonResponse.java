package az.dev.useraccountsapplication.dto.response;

import java.io.Serializable;

public class CommonResponse<E> implements Serializable {
    private ErrorResponse errorResponse;
    private E data;

    public ErrorResponse getStatus() {
        return errorResponse;
    }

    public void setStatus(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public CommonResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public CommonResponse(ErrorResponse errorResponse, E data) {
        this.errorResponse = errorResponse;
        this.data = data;
    }
}
