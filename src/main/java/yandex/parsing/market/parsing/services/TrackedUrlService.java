package yandex.parsing.market.parsing.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yandex.parsing.market.parsing.dto.TrackedUrlCreateDTO;
import yandex.parsing.market.parsing.models.TrackedUrl;
import yandex.parsing.market.parsing.models.User;
import yandex.parsing.market.parsing.repositories.TrackedUrlRepository;
import yandex.parsing.market.parsing.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackedUrlService {
    private TrackedUrlRepository trackedUrlRepository;
    private final UserRepository userRepository;
    public TrackedUrlService(TrackedUrlRepository trackedUrlRepository, UserRepository userRepository) {
        this.trackedUrlRepository = trackedUrlRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> addToUserUrl(TrackedUrlCreateDTO trackedUrlCreateDTO,
                                               Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TrackedUrl trackedUrl = new TrackedUrl();
        trackedUrl.setUrl(trackedUrlCreateDTO.getUrl());
        trackedUrl.setUser(userOptional.get());
        trackedUrlRepository.save(trackedUrl);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<TrackedUrlCreateDTO>> getAllByUser(Long userId) {
        List<TrackedUrl> urls = trackedUrlRepository.findAllByUser_Id(userId);
        List<TrackedUrlCreateDTO> dtos = urls.stream()
                .map(url -> new TrackedUrlCreateDTO(
                        url.getUrl()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
