package flab.emojeomo.user.controller;

import flab.emojeomo.global.enums.BaseException;
import flab.emojeomo.global.response.ExceptionResponseDto;
import flab.emojeomo.global.response.NormalResponseDto;
import flab.emojeomo.global.response.ResponseDto;
import flab.emojeomo.global.response.ResponseType;
import flab.emojeomo.oauth.common.OAuthProfileResponse;
import flab.emojeomo.oauth.kakao.KakaoGetAuthorization;
import flab.emojeomo.user.dto.LoginRequest;
import flab.emojeomo.user.dto.LoginResponse;
import flab.emojeomo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final KakaoGetAuthorization kakaoGetAuthorization;
    private final UserService userService;

    @RequestMapping("/oauth/login/kakao")
    public ResponseDto kakaoLogin(@RequestParam String authorizationCode) {
        try {
            String accessToken = kakaoGetAuthorization.requestAccessToken(authorizationCode);
            OAuthProfileResponse oAuthProfileResponse = kakaoGetAuthorization.requestOauthProfile(accessToken);

            // redirect to 우리 서버의 회원가입 or 로그인 컨트롤러
            LoginResponse loginResponse = userService.oauthLogin(oAuthProfileResponse);
            return new NormalResponseDto<>(ResponseType.OK, loginResponse);
        } catch (BaseException e) {
            return e.getExceptionResponseDto();
        } catch (Exception e) {
            return new ExceptionResponseDto(ResponseType.INVALID_PARAMETER, e.getMessage());
        }
    }

    @RequestMapping("/oauth/login")
    public ResponseDto ourLogin (@RequestBody LoginRequest request) {
        try {
            LoginResponse loginResponse = userService.afterOauthLogin(request);
            return new NormalResponseDto<>(ResponseType.OK, loginResponse);
        }
        catch (BaseException e) {
            return e.getExceptionResponseDto();
        }
        catch (Exception e) {
            return new ExceptionResponseDto(ResponseType.INVALID_PARAMETER, e.getMessage());
        }
    }
}
