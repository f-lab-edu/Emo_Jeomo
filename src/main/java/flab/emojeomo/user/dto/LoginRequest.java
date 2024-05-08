package flab.emojeomo.user.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private Long userIdx;
    private String token;
}
