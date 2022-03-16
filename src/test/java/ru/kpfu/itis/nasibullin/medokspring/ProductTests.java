package ru.kpfu.itis.nasibullin.medokspring;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kpfu.itis.nasibullin.medokspring.dto.ProductAddDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;
import ru.kpfu.itis.nasibullin.medokspring.services.product.ProductService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@AutoConfigureEmbeddedDatabase(beanName = "dataSource", refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests {
    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    void testNonExistingProduct() {
        assertThatThrownBy(() -> productService.findById(1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Order(2)
    void testProductCreate() {
        ProductAddDto dto = new ProductAddDto();
        dto.setName("Honey");
        dto.setCategory(ProductCategory.LIQUID);
        dto.setDescription("Good honey");
        dto.setPrice(750);
        dto.setRemaining(5);
        dto.setSize(1.5);
        Product product = dto.toProduct();
        Product savedProduct = productService.save(product);

        assertThat(savedProduct.getProductId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(product.getName());
        assertThat(savedProduct.getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    @Order(3)
    void testProductGet() {
        assertThat(productService.findById(1)).isNotNull();
    }

    @Test
    @Order(4)
    void testFindByCategory() {
        List<Product> products = productService.findAllByCategory(ProductCategory.LIQUID);
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    @Order(5)
    void testProductEdit() {
        Product product = productService.findById(1);

        String newName = "AAAAAAAAAAAA";
        String newDescription = "AaAaAaAaAa";

        product.setName(newName);
        product.setDescription(newDescription);

        product = productService.update(product);

        assertThat(product.getProductId()).isNotNull();
        assertThat(product.getName()).isEqualTo(newName);
        assertThat(product.getDescription()).isEqualTo(newDescription);
    }

    @Test
    @Order(6)
    void testProductDelete() {
        productService.delete(1);
        assertThatThrownBy(() -> productService.findById(1)).isInstanceOf(IllegalArgumentException.class);
    }
}
