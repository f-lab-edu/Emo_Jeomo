package flab.emojeomo.oauth.common;

import flab.emojeomo.global.enums.LoginProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthProfileResponse {
    Long identifier;
    String nickname;
    LoginProvider loginProvider;
}
