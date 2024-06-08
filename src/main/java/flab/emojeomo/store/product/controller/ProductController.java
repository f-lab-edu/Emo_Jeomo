package flab.emojeomo.store.product.controller;
import flab.emojeomo.global.enums.BaseException;
import flab.emojeomo.global.response.ExceptionResponseDto;
import flab.emojeomo.global.response.NormalResponseDto;
import flab.emojeomo.global.response.ResponseDto;
import flab.emojeomo.global.response.ResponseType;
import flab.emojeomo.store.product.dto.ProductCreateDto;
import flab.emojeomo.store.product.dto.ProductInfoUpdateDto;
import flab.emojeomo.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductController {

    // TODO : 나중에 판매자 등록용 백오피스 API ~ 컨트롤러로 빼야할거같음
    private final ProductService productService;

    @PostMapping
    @RequestMapping("/seller/management/product/registration")
    public ResponseDto registerProduct(@RequestBody ProductCreateDto productCreateDto) {
        try {
            productService.createProduct(productCreateDto);
            return new NormalResponseDto<>(ResponseType.OK, ResponseType.OK.getMessage());
        } catch (BaseException e) {
            return e.getExceptionResponseDto();
        } catch (Exception e) {
            return new ExceptionResponseDto(ResponseType.INVALID_PARAMETER, e.getMessage());
        }
    }

    @PutMapping
    @RequestMapping("/seller/management/product/modification")
    public ResponseDto updateProduct(@RequestBody ProductInfoUpdateDto productInfoUpdateDto) {
        try {
            productService.updateProductInfo(productInfoUpdateDto);
            return new NormalResponseDto<>(ResponseType.OK, ResponseType.OK.getMessage());
        } catch (BaseException e) {
            return e.getExceptionResponseDto();
        } catch (Exception e) {
            return new ExceptionResponseDto(ResponseType.INVALID_PARAMETER, e.getMessage());
        }
    }

    @PutMapping
    @RequestMapping("/seller/management/product")
    public ResponseDto deleteProduct(@RequestParam Long productIdx) {
        try {
            productService.deleteProduct(productIdx);
            return new NormalResponseDto<>(ResponseType.OK, ResponseType.OK.getMessage());
        } catch (BaseException e) {
            return e.getExceptionResponseDto();
        } catch (Exception e) {
            return new ExceptionResponseDto(ResponseType.INVALID_PARAMETER, e.getMessage());
        }
    }
}
