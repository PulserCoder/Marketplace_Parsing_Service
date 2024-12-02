package yandex.parsing.market.parsing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yandex.parsing.market.parsing.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

}
