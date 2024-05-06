package flab.emojeomo.user.service;

import flab.emojeomo.global.enums.BaseException;
import flab.emojeomo.global.response.ResponseType;
import flab.emojeomo.oauth.common.OAuthProfileResponse;
import flab.emojeomo.user.domain.Member;
import flab.emojeomo.user.dto.LoginRequest;
import flab.emojeomo.user.dto.LoginResponse;
import flab.emojeomo.user.dto.MemberCreateDto;
import flab.emojeomo.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static flab.emojeomo.global.util.JsonWebTokenGenerator.generateJsonWebToken;
import static flab.emojeomo.global.util.JsonWebTokenGenerator.generateRefreshToken;

@RequiredArgsConstructor
@Service
public class UserService {

    private final MemberRepository memberRepository;

    public LoginResponse afterOauthLogin(LoginRequest request) throws Exception {
        Member member = memberRepository.findById(request.getUserIdx())
                                        .orElseThrow(() ->
                                                new BaseException(ResponseType.INVALID_USER, ResponseType.INVALID_USER.getMessage())
                                        );
        return makeLoginSuccess(member.getIdx(), member.getNickname());
    }

    /**
     * 아예 서비스에 처음 가입하는 경우
     * 또는 토큰이 만료되어서 다시 인증하는 경우에 거치게끔..?
     */
    public LoginResponse oauthLogin(OAuthProfileResponse profile) {
        Member member = memberRepository.findByNickname(profile.getNickname())
                                        .orElseGet(() -> createMember(new MemberCreateDto(profile)));

        return makeLoginSuccess(member.getIdx(), member.getNickname());
    }

    @Transactional
    private Member createMember(MemberCreateDto memberCreateDto) {
        return memberRepository.save(memberCreateDto.createMemberEntity());
    }

    private LoginResponse makeLoginSuccess(Long idx, String nickname) {
        return LoginResponse.builder()
                            .userIdx(idx)
                            .jsonWebToken(generateJsonWebToken(nickname))
                            .refreshToken(generateRefreshToken())
                            .build();
    }
}
