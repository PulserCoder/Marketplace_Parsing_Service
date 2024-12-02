package yandex.parsing.market.parsing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tracked_url_id")
    private TrackedUrl trackedUrl;

    private Integer price;
    private LocalDateTime timestamp;


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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
