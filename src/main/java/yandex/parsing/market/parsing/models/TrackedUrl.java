package yandex.parsing.market.parsing.models;

import jakarta.persistence.*;
import yandex.parsing.market.parsing.validators.annotations.ValidUrl;

import java.util.List;

@Entity
public class TrackedUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ValidUrl
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "trackedUrl", cascade = CascadeType.ALL)
    private Product product;

    @OneToMany(mappedBy = "trackedUrl", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Measurement> measurements;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
