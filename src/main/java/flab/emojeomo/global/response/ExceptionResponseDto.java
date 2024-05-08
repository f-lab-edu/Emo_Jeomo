package flab.emojeomo.global.response;

import lombok.Getter;

@Getter
public class ExceptionResponseDto extends ResponseDto{
    public ExceptionResponseDto(ResponseType responseType, String message) {
        super(responseType, message);
    }

}
