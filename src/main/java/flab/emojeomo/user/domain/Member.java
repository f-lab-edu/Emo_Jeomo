package flab.emojeomo.user.domain;

import flab.emojeomo.global.enums.LoginProvider;
import flab.emojeomo.global.enums.Rate;
import flab.emojeomo.global.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 50)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Rate rate;

    @Enumerated(EnumType.STRING)
    private LoginProvider loginProvider;

    private LocalDateTime createdDate;

    private int reserveSum; // 최초 생성시의 적립금은 정책상의 고정 값으로 ...

    private Long identifier;

}
