package br.com.zupacademy.yudi.mercadolivre.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ImageUploader {
    Set<String> upload(List<MultipartFile> images);
}
