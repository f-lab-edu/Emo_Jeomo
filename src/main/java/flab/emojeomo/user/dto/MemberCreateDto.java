package flab.emojeomo.user.dto;

import flab.emojeomo.global.enums.LoginProvider;
import flab.emojeomo.global.enums.Rate;
import flab.emojeomo.global.enums.Role;
import flab.emojeomo.oauth.common.OAuthProfileResponse;
import flab.emojeomo.user.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberCreateDto {
    private final String nickname;

    private final Role role = Role.MEMBER;

    private final Rate rate = Rate.BRONZE;

    private final LoginProvider loginProvider;

    private final int reserveSum = 1000;

    private final Long identifier;

    public MemberCreateDto(OAuthProfileResponse profile) {
        this.nickname = profile.getNickname();
        this.loginProvider = profile.getLoginProvider();
        this.identifier = profile.getIdentifier();
    }

    public Member createMemberEntity() {
        return Member.builder()
                     .nickname(this.nickname)
                     .role(this.role)
                     .rate(this.rate)
                     .loginProvider(this.loginProvider)
                     .createdDate(LocalDateTime.now())
                     .reserveSum(this.reserveSum)
                     .identifier(this.identifier)
                     .build();
    }
}
