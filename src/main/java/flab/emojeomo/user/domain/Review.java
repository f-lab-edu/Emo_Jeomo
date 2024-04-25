package flab.emojeomo.user.domain;

import flab.emojeomo.global.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long idx;

    private Long memberIdx;

    private Long productIdx;

    @Column(length = 100)
    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
