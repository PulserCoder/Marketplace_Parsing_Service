package yandex.parsing.market.parsing.dto;

import yandex.parsing.market.parsing.validators.annotations.ValidUrl;

public class TrackedUrlCreateDTO {
    @ValidUrl
    private String url;

    public TrackedUrlCreateDTO() {}

    public TrackedUrlCreateDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
