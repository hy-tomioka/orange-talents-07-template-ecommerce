package br.com.zupacademy.yudi.mercadolivre.product;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinions {

    private final List<ProductOpinion> opinions;

    public Opinions(List<ProductOpinion> opinions) {
        this.opinions = opinions;
    }

    public <T> List<T> map(Function<ProductOpinion, T> mappingFunction) {
        return this.opinions.stream().map(mappingFunction).collect(Collectors.toList());
    }

    public Double computeAverageRating() {
        OptionalDouble average = opinions.stream().mapToInt(ProductOpinion::getRating).average();
        return average.orElse(0d);
    }

    public Integer getSize() {
        return opinions.size();
    }
}
