package flab.emojeomo.store.mall.domain;

import flab.emojeomo.global.enums.Status;
import flab.emojeomo.global.enums.Type;
import jakarta.persistence.*;

@Entity
public class Mall {

    @Id
    @GeneratedValue
    private Long idx;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, length = 200)
    private String detail;
}
