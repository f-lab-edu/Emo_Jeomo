package flab.emojeomo.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto {
    protected String code;
    protected HttpStatus httpStatus;
    protected String message;

    public ResponseDto (ResponseType responseType) {
        this.code = responseType.getStatusCode();
        this.httpStatus = responseType.getHttpStatus();
        this.message = responseType.getMessage();
    }

    public ResponseDto (ResponseType responseType, String message) {
        this.code = responseType.getStatusCode();
        this.httpStatus = responseType.getHttpStatus();
        this.message = message;
    }
}
