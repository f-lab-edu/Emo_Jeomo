package flab.emojeomo.store.product.repository;

import flab.emojeomo.store.product.domain.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

}
