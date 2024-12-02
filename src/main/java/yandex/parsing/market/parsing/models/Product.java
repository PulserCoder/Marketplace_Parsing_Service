package yandex.parsing.market.parsing.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private String title;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "tracked_url_id")
    private TrackedUrl trackedUrl;


    public TrackedUrl getTrackedUrl() {
        return trackedUrl;
    }

    public void setTrackedUrl(TrackedUrl trackedUrl) {
        this.trackedUrl = trackedUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
