package flab.emojeomo.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ReviewImage {
    @Id
    @GeneratedValue
    private Long idx;

    private Long reviewIdx;

    @Column(length = 500)
    private String img;
}
