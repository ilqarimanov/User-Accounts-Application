package az.dev.useraccountsapplication.exception;


import az.dev.useraccountsapplication.dto.response.ErrorResponse;
import az.dev.useraccountsapplication.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomException(CustomNotFoundException e) {
        return ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentTypeMismatchException e) {
        String paramName = e.getParameter().getParameter().getName();
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(paramName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ErrorResponse handleValidation(MethodArgumentNotValidException e){
            String fieldName = e.getBindingResult().getFieldError().getField();

        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(fieldName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();

    }
}
