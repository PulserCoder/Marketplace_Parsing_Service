package yandex.parsing.market.parsing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yandex.parsing.market.parsing.models.TrackedUrl;
import yandex.parsing.market.parsing.models.User;

import java.util.List;

@Repository
public interface TrackedUrlRepository extends JpaRepository<TrackedUrl, Long> {
    List<TrackedUrl> findAllByUser_Id(Long userId);
}
