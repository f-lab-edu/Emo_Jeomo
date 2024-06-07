package flab.emojeomo.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseType {
    OK("200000", "OK", HttpStatus.OK),
    MISSING_PARAMETER("400001", "Missing Parameter", HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER("400002", "Invalid Argument", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED("400003", "Token Expired", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("400004", "Invalid Token", HttpStatus.BAD_REQUEST),

    INVALID_NUMBER("400005", "Parameter Number is in Invalid Numeric Range", HttpStatus.BAD_REQUEST),
    INVALID_MALL("404001", "Invalid Mall", HttpStatus.NOT_FOUND),
    INVALID_PRODUCT("404002", "Invalid Product", HttpStatus.BAD_REQUEST),
    ALREADY_EXIST_USER("400100", "Already Exist User", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_REQUEST("400101", "Unauthorized Request", HttpStatus.BAD_REQUEST),

    UNSUPPORTED_MEDIA_TYPE("415001", "Unsupported Media Type", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    INTERNAL_SERVER_ERROR("500000", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String statusCode;
    private final String message;
    private final HttpStatus httpStatus;
    ResponseType(String statusCode, String message, HttpStatus httpStatus) {
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}