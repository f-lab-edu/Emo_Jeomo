package flab.emojeomo.user.service;

import flab.emojeomo.global.enums.BaseException;
import flab.emojeomo.global.response.ResponseType;
import flab.emojeomo.global.util.JsonWebTokenGenerator;
import flab.emojeomo.oauth.common.OAuthProfileResponse;
import flab.emojeomo.user.domain.Member;
import flab.emojeomo.user.dto.LoginRequest;
import flab.emojeomo.user.dto.LoginResponse;
import flab.emojeomo.user.dto.MemberCreateDto;
import flab.emojeomo.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final MemberRepository memberRepository;
    private final JsonWebTokenGenerator jsonWebTokenGenerator;

    public LoginResponse afterOauthLogin(LoginRequest request) throws Exception {
        Member member = memberRepository.findById(request.getUserIdx())
                                        .orElseThrow(() ->
                                                new BaseException(ResponseType.INVALID_USER, ResponseType.INVALID_USER.getMessage())
                                        );

        return makeLoginSuccess(member.getIdx(), jsonWebTokenGenerator.generateJsonWebToken(member.getIdx()));
    }

    /**
     * 아예 서비스에 처음 가입하는 경우
     * 또는 토큰이 만료되어서 다시 인증하는 경우에 거치게끔..?
     */
    public LoginResponse oauthLogin(OAuthProfileResponse profile) {
        Member member = memberRepository.findByIdentifierAndLoginProvider(profile.getIdentifier(), profile.getLoginProvider())
                                        .orElseGet(() -> createMember(new MemberCreateDto(profile)));

        return makeLoginSuccess(member.getIdx(), jsonWebTokenGenerator.generateJsonWebToken(member.getIdx()));
    }

    @Transactional
    private Member createMember(MemberCreateDto memberCreateDto) {
        Member member = memberCreateDto.createMemberEntity();
        return memberRepository.save(member);
    }

    private LoginResponse makeLoginSuccess(Long idx, String token) {
        return LoginResponse.builder()
                            .userIdx(idx)
                            .jsonWebToken(token)
                            .refreshToken(jsonWebTokenGenerator.generateRefreshToken())
                            .build();
    }
}
