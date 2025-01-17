package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.product.dto.ProductImageRequest;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductImageController {

    private final ImageUploader imageUploader;
    private final ProductRepository productRepository;

    public ProductImageController(ImageUploader imageUploader, ProductRepository productRepository) {
        this.imageUploader = imageUploader;
        this.productRepository = productRepository;
    }

    @PostMapping(value = "/{id}/images")
    public void upload(@Valid ProductImageRequest request, @PathVariable(name = "id") Long id,
                       @AuthenticationPrincipal User user) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!product.belongsTo(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "User is not allowed to upload images for this product");
        }
        product.uploadAndBindImages(imageUploader, request.getImages());
        productRepository.save(product);
    }
}
