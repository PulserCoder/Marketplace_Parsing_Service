package yandex.parsing.market.parsing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yandex.parsing.market.parsing.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
