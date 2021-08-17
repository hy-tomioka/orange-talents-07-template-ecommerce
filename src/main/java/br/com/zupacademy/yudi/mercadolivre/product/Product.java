package br.com.zupacademy.yudi.mercadolivre.product;

import br.com.zupacademy.yudi.mercadolivre.category.Category;
import br.com.zupacademy.yudi.mercadolivre.product.dto.NewCharacteristicRequest;
import br.com.zupacademy.yudi.mercadolivre.user.User;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(nullable = false)
    private Long quantity;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = PERSIST)
    private Set<Characteristic> characteristics = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = MERGE)
    private Set<ProductImage> images = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private List<ProductOpinion> opinions = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @OrderBy("question ASC")
    private Set<ProductQuestion> questions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    /**
     * Only for Hibernate use
     */
    @Deprecated
    private Product() {
    }

    public Product(String name, BigDecimal price, Long quantity, String description, Category category,
                   Collection<NewCharacteristicRequest> characteristics, User seller) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.characteristics.addAll(characteristics.stream().map(c -> c.toCharacteristic(this))
                .collect(Collectors.toSet()));
        this.seller = seller;
    }

    public void uploadAndBindImages(ImageUploader imageUploader, List<MultipartFile> images) {
        Set<String> imagesLocation = imageUploader.upload(images);
        bindImages(imagesLocation);
    }

    private void bindImages(Set<String> location) {
        Assert.notEmpty(location, "Images are required.");
        this.images.addAll(location.stream().map(image -> new ProductImage(image, this))
                .collect(Collectors.toSet()));
    }

    public boolean belongsTo(User user) {
        return (user.equals(this.seller));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public Set<Characteristic> getCharacteristics() {
        return Collections.unmodifiableSet(characteristics);
    }

    public String getSellerLogin() {
        return seller.getUsername();
    }

    public Set<ProductImage> getImages() {
        return Collections.unmodifiableSet(images);
    }

    public Opinions getOpinions() {
        return new Opinions(opinions);
    }

    public Set<ProductQuestion> getQuestions() {
        return Collections.unmodifiableSet(questions);
    }

    public <T> Collection<T> mapCharacteristics(
            Function<Characteristic, T> mappingFunction) {
        return this.characteristics.stream().map(mappingFunction).collect(Collectors.toSet());
    }

    public <T> Collection<T> mapImages(Function<ProductImage, T> mappingFunction) {
        return this.images.stream().map(mappingFunction).collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapQuestions(Function<ProductQuestion, T> mappingFunction) {
        return this.questions.stream().map(mappingFunction).collect(Collectors.toCollection(TreeSet::new));
    }
}
