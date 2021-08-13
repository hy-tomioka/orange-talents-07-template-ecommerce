package br.com.zupacademy.yudi.mercadolivre.product;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FakeImageUploader implements ImageUploader {
    public Set<String> upload(List<MultipartFile> images) {
        return images.stream().map(i -> "http://io.bucket/"+i.getOriginalFilename()).collect(Collectors.toSet());
    }
}
