package flab.emojeomo.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoLoginRequest {
    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    private String code;
}
