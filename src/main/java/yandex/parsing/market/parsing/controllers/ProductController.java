package yandex.parsing.market.parsing.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yandex.parsing.market.parsing.models.Measurement;
import yandex.parsing.market.parsing.models.Product;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @GetMapping("get_all/{user_id}")
    public List<Product> getAllUserProducts(@PathVariable("user_id") String userId) {
        return null;
    }

    @GetMapping("get_history/{product_id}")
    public List<Measurement> getHistoryOfProduct(@PathVariable("product_id") String productId) {
        return null;
    }


}
