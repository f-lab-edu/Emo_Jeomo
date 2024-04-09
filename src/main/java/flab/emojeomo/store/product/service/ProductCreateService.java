package flab.emojeomo.store.product.service;

import flab.emojeomo.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCreateService {
    private final ProductRepository productRepository;



}
