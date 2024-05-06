package flab.emojeomo.user.controller;

import flab.emojeomo.oauth.common.OAuthProfileResponse;
import flab.emojeomo.oauth.kakao.KakaoGetAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final KakaoGetAuthorization kakaoGetAuthorization;

    @RequestMapping("/oauth/login/kakao")
    public String kakaoLogin(@RequestParam String authorizationCode) {
        String accessToken = kakaoGetAuthorization.requestAccessToken(authorizationCode);
        OAuthProfileResponse oAuthProfileResponse = kakaoGetAuthorization.requestOauthProfile(accessToken);

        // redirect to 우리 서버의 회원가입 or 로그인 컨트롤러
        // rest api의 redirect..?
        return "redirect:/user/login";
    }

}
