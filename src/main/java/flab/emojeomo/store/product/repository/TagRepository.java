package flab.emojeomo.store.product.repository;

import flab.emojeomo.store.product.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
