package com.shopme.product;

import java.util.List;

import com.shopme.common.entity.Category;
import com.shopme.common.entity.Product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTests {
    @Autowired ProductRepository repo;

    @Test 
    public void testFindByAlias(){
        String alias = "asus-laptops";
        Product product = repo.findByAlias(alias);

        assertThat(product).isNotNull();
    }
}
