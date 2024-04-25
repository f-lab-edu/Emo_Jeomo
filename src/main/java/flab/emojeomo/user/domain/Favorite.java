package flab.emojeomo.user.domain;

import jakarta.persistence.Entity;

@Entity
public class Favorite {
    private Long idx;
    private Long memberIdx;
    private Long mallIdx;
}
