package yandex.parsing.market.parsing.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yandex.parsing.market.parsing.dto.TrackedUrlCreateDTO;
import yandex.parsing.market.parsing.models.TrackedUrl;
import yandex.parsing.market.parsing.services.TrackedUrlService;

import java.util.List;

@RestController
@RequestMapping("url")
public class UrlController {
    private TrackedUrlService trackedUrlService;

    public UrlController(TrackedUrlService trackedUrlService) {
        this.trackedUrlService = trackedUrlService;
    }


    @PostMapping(value = "add/{user_id}")
    public ResponseEntity<String> addUrl(@RequestBody TrackedUrlCreateDTO url,
                                 @PathVariable("user_id") Long userId) {
        return trackedUrlService.addToUserUrl(url, userId);
    }

    @GetMapping(value = "get_by_user/{user_id}")
    public ResponseEntity<List<TrackedUrlCreateDTO>> getUrlByUser(@PathVariable("user_id") Long userId) {
        return trackedUrlService.getAllByUser(userId);
    }



}
