package flab.emojeomo.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class NormalResponseDto<T> extends ResponseDto {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    public NormalResponseDto(ResponseType responseType) {
        super(responseType);
    }

    public NormalResponseDto(ResponseType responseType, String message) {
        super(responseType, message);
    }

    public NormalResponseDto(ResponseType responseType, T data) {
        super(responseType);
        this.data = data;
    }

    public NormalResponseDto(ResponseType responseType, String message, T data) {
        super(responseType, message);
        this.data = data;
    }
}
