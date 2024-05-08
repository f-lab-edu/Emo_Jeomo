package flab.emojeomo.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private Long userIdx;
    private String jsonWebToken;
    private String refreshToken;
}
