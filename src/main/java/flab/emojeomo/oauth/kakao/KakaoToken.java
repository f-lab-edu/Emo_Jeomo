package flab.emojeomo.oauth.kakao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

// kauth.kakao.com에 인가코드 주고 받을 수 있는 응답
// 인가 코드는 클라이언트 측에서 로그인 후 내 쪽으로 넘겨준다.
@Getter
@NoArgsConstructor
public class KakaoToken {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;

    @JsonProperty("scope")
    private String scope;
}
