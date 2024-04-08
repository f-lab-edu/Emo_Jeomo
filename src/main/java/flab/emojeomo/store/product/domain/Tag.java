package flab.emojeomo.store.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Tag {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(nullable = false, length = 50)
    private String content;

    @JsonIgnore
    @OneToMany(mappedBy = "tag")
    private List<ProductTag> productTags;
}
