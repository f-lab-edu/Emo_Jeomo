package flab.emojeomo.user.domain;

import flab.emojeomo.global.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserves {
    @Id
    @GeneratedValue
    private Long idx;

    // MemberIdx 참조 걸어야 할것 같기도
    private Long memberIdx;

    private int amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdDate;
}
