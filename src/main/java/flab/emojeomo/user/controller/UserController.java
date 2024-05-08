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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final KakaoGetAuthorization kakaoGetAuthorization;
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/oauth/login/kakao")
    public ResponseDto kakaoLogin(@RequestParam("code") String code) {
        try {

            String accessToken = kakaoGetAuthorization.requestAccessToken(code);
            OAuthProfileResponse oAuthProfileResponse = kakaoGetAuthorization.requestOauthProfile(accessToken);
            LoginResponse loginResponse = userService.oauthLogin(oAuthProfileResponse);

            return new NormalResponseDto<>(ResponseType.OK, loginResponse);

        } catch (BaseException e) {
            return e.getExceptionResponseDto();
        } catch (Exception e) {
            return new ExceptionResponseDto(ResponseType.INVALID_PARAMETER, e.getMessage());
        }
    }

    @PostMapping("/oauth/login")
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
