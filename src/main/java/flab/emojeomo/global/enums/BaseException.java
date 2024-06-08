package flab.emojeomo.global.enums;

import flab.emojeomo.global.response.ExceptionResponseDto;
import flab.emojeomo.global.response.ResponseType;
import org.springframework.util.StringUtils;

public class BaseException extends RuntimeException {

    private final ResponseType responseType;
    private final String message;

    public BaseException(ResponseType responseType, String message) {
        this.responseType = responseType;
        if (StringUtils.hasLength(message)) {
            this.message = message;
        } else {
            this.message =  responseType.getMessage();
        }
    }

    public ExceptionResponseDto getExceptionResponseDto() {
        return new ExceptionResponseDto(responseType,  message);
    }
}
