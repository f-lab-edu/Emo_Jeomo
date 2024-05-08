package flab.emojeomo.user.repository;

import flab.emojeomo.global.enums.LoginProvider;
import flab.emojeomo.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByIdentifierAndLoginProvider(Long identifier, LoginProvider loginProvider);
}