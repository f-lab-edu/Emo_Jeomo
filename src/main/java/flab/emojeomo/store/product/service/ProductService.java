package flab.emojeomo.store.product.service;

import flab.emojeomo.global.enums.BaseException;
import flab.emojeomo.global.response.ResponseType;
import flab.emojeomo.store.product.domain.Product;
import flab.emojeomo.store.product.domain.ProductOption;
import flab.emojeomo.store.product.domain.ProductStock;
import flab.emojeomo.store.product.dto.ProductCreateDto;
import flab.emojeomo.store.product.dto.ProductOptionCreateDto;
import flab.emojeomo.store.product.dto.ProductStockCreateDto;
import flab.emojeomo.store.product.repository.ProductOptionRepository;
import flab.emojeomo.store.product.repository.ProductRepository;
import flab.emojeomo.store.product.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static flab.emojeomo.global.enums.LimitNums.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductStockRepository productStockRepository;

    public void createProduct (ProductCreateDto productCreateDto) {
        // TODO : 존재하는 몰인지 검사 여부가 필요하다.

        // 가격 적정한가
        if (productCreateDto.getPrice() < MIN_NUM || productCreateDto.getPrice() > MAX_PRICE) {
            throw new BaseException(ResponseType.INVALID_MALL, null);
        }

        // 이름 길이 제한
        if (productCreateDto.getName().length() < MIN_NUM|| productCreateDto.getName().length() > MAX_PRODUCT_NAME) {
            throw new BaseException(ResponseType.INVALID_NUMBER, null);
        }

        // 이미지도 무조건 같이 들어가야할 거 같은데
        productRepository.save(productCreateDto.toProductEntity());
    }

    // 상품 옵션 입력
    public void createProductOption(ProductOptionCreateDto optionDto) {
        Product product = productRepository.findById(optionDto.getProductIdx())
                                           .orElseThrow(() -> new BaseException(ResponseType.INVALID_PRODUCT, null));

        // option Entity 생성 및 save
        ProductOption productOption = optionDto.makeProductOption(product);
        productOptionRepository.save(productOption);
    }

    // 상품 재고 입력
    public void createProductStock(ProductStockCreateDto stockCreateDto) {
        // 해당 상품의 옵션이 있어? (상품은 굳이 검사하지 않음, 옵션이 있다는 것 자체가 상품이 있다는 것이므로)
        ProductOption productOption = productOptionRepository.findById(stockCreateDto.getOptionIdx())
                                                             .orElseThrow(() -> new BaseException(ResponseType.INVALID_PRODUCT, null));

        // 재고개수의 유효성 확인
        if (stockCreateDto.getCount() <= MIN_NUM || stockCreateDto.getCount() > MAX_STOCK) {
            throw new BaseException(ResponseType.INVALID_NUMBER, null);
        }
        // 상품 재고 등록
        ProductStock productStock = stockCreateDto.createProductStock(productOption);
        productStockRepository.save(productStock);
    }
}
