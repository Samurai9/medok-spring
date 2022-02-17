package ru.kpfu.itis.nasibullin.medokspring.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.kpfu.itis.nasibullin.medokspring.entities.Basket;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;

public class BasketUtils {
    public static Map<Product, Integer> getHashMap(Basket basket) {
        Map<Product, Integer> res = new HashMap<>();
        List<Product> products = basket.getProducts();
        for (Product product: products) {
            if (res.containsKey(product)) {
                res.replace(product, res.get(product) + 1);
            } else {
                res.put(product, 1);
            }
        }
        return res;
    }
}
