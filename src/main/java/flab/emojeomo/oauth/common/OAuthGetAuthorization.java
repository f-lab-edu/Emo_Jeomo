package flab.emojeomo.oauth.common;

import flab.emojeomo.user.dto.LoginResponse;

/**
 * 각 서버 단에 인가 코드를 가지고 오는 것
 * 그 인가 코드로 사용자의 프로필 정보를 갖고 오는 것
 * 역할이 총 2가지가 되는데
 * 클래스(인터페이스) 네이밍이 적절한지,
 * 이것도 SRP에 따라 분리를 해놓는 것이 적합한지
 */
public interface OAuthGetAuthorization {
    String requestAccessToken(String authorizationToken);
    OAuthProfileResponse requestOauthProfile(String accessToken);
}
