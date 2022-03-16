package ru.kpfu.itis.nasibullin.medokspring;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.kpfu.itis.nasibullin.medokspring.controllers.rest.RestProductController;
import ru.kpfu.itis.nasibullin.medokspring.dto.ProductAddDto;
import ru.kpfu.itis.nasibullin.medokspring.entities.Product;
import ru.kpfu.itis.nasibullin.medokspring.entities.ProductCategory;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureEmbeddedDatabase(beanName = "dataSource", refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestProductControllerTests {

    @Autowired
    private RestProductController restProductController;

    @Test
    @Order(1)
    public void testGetNonExistProduct() {
        assertThat(restProductController.getProduct(1).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    @Order(2)
    public void testProductAdd() {
        ProductAddDto dto = new ProductAddDto();
        dto.setName("Honey");
        dto.setCategory(ProductCategory.LIQUID);
        dto.setDescription("Good honey");
        dto.setPrice(750);
        dto.setRemaining(5);
        dto.setSize(1.5);
        Product product = dto.toProduct();
        assertThat(restProductController.addProduct(product).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(3)
    public void testGetProduct() {
        ResponseEntity<Product> productResponseEntity = restProductController.getProduct(1);
        assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productResponseEntity.getBody()).isNotNull();
        System.out.println(productResponseEntity.getBody().getName() + " AAAAAAAAAAAAAAAAAAa");
        Product product = productResponseEntity.getBody();
        assertThat(product).isNotNull();
        assertThat(product.getProductId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("Honey");
        assertThat(product.getDescription()).isEqualTo("Good honey");
        assertThat(product.getPrice()).isEqualTo(750);
        assertThat(product.getRemaining()).isEqualTo(5);
        assertThat(product.getSize()).isEqualTo(1.5);
    }

    @Test
    @Order(4)
    public void testEditProduct() {
        ResponseEntity<Product> productResponseEntity = restProductController.getProduct(1);
        assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Product product = productResponseEntity.getBody();
        assertThat(product).isNotNull();

        String newName = "AAAAAAAAAAAA";
        String newDescription = "AaAaAaAaAa";

        product.setName(newName);
        product.setDescription(newDescription);

        productResponseEntity = restProductController.updateProduct(1, product);
        assertThat(productResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        product = productResponseEntity.getBody();
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo(newName);
        assertThat(product.getDescription()).isEqualTo(newDescription);
    }


    @Test
    @Order(5)
    public void testRemoveProduct() {
        assertThat(restProductController.deleteProduct(1).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(restProductController.getProduct(1).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
