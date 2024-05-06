package flab.emojeomo.oauth.kakao;

import flab.emojeomo.global.enums.LoginProvider;
import flab.emojeomo.oauth.common.OAuthProfileResponse;
import flab.emojeomo.oauth.common.OAuthGetAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KakaoGetAuthorization implements OAuthGetAuthorization {

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate;

    /**
     * 사용자의 동의 후 인가코드를 가지고 프로필을 가져오기 위한 토큰을 받아오는 메소드
     * @param code : 로그인을 위한 인가 코드
     * @return accessToken : profile 가져오기 위한 토큰
     */
    @Override
    public String requestAccessToken(String code) {

        String authorizationUrl = "https://kauth.kakao.com/oauth/authorize";

        HttpHeaders header = new org.springframework.http.HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        KakaoLoginRequest kakaoLoginParams = KakaoLoginRequest.builder()
                                                            .grantType("grant_type")
                                                            .clientId(clientId)
                                                            .redirectUri(redirectUri)
                                                            .code(code)
                                                            .build();


        HttpEntity<KakaoLoginRequest> request = new HttpEntity<>(kakaoLoginParams, header);
        KakaoLoginResponse kakaoLoginResponse = restTemplate.postForObject(authorizationUrl, request, KakaoLoginResponse.class);

        assert kakaoLoginResponse != null;
        return kakaoLoginResponse.getAccessToken();
    }

    @Override
    public OAuthProfileResponse requestOauthProfile(String accessToken) {
        String profileUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.profile\"]");

        HttpEntity<?> request = new HttpEntity<>(body, header);
        KakaoProfileResponse response = restTemplate.postForObject(profileUrl, request, KakaoProfileResponse.class);
        assert response != null;

        Long id = response.getId();
        String nickname = response.getKakaoAccount().getProfile().getNickname();
        return OAuthProfileResponse.builder()
                                   .identifier(id)
                                   .nickname(nickname)
                                   .loginProvider(LoginProvider.KAKAO)
                                   .build();
    }
}
