package com;

import com.model.Product;
import com.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    //@Test
    public void testFindByProductName() {

        Product product = new Product();

        product.setId(5);
        product.setCategory("testCategory");
        product.setName("testName");
        product.setQuantity(3);
        product.setPrice(12f);

        productRepository.save(product);

        Product findByProductName = productRepository.findOne(product.getId());

        assertThat(findByProductName).hasFieldOrPropertyWithValue("name", "testName");
    }
} */
