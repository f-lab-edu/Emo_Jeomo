package flab.emojeomo.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Wish {
    @Id
    @GeneratedValue
    private Long idx;

    private Long memberIdx;

    private Long mallIdx;

    private Long productIdx;
}
