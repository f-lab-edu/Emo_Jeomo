package flab.emojeomo.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * struct 구조
 *  "id " : 203432424L(Long type)
 *  "kakao_account" : {
 *      "profile" : {
 *          "nickname" : ""
 *      }
 *  }
 */

@Getter
public class KakaoProfileResponse {
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    static class KakaoAccount {
        private KakaoProfile profile;
    }

    @Getter
    static class KakaoProfile {
        private String nickname;
    }
}
