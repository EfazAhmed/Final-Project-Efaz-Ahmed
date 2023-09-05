package com.company.gamestore.repository;

import com.company.gamestore.model.TShirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepository;

    private TShirt tShirt1;
    private TShirt tShirt2;

    @BeforeEach
    public void setUp() {
        tShirtRepository.deleteAll();
        tShirt1 = new TShirt();
        tShirt1.setSize("Small");
        tShirt1.setColor("Blue");
        tShirt1.setDescription("100% cotton t-shirt made in USA");
        tShirt1.setPrice(BigDecimal.valueOf(20.99));
        tShirt1.setQuantity(3);
        tShirt1 = tShirtRepository.save(tShirt1);

        tShirt2 = new TShirt();
        tShirt2.setSize("Large");
        tShirt2.setColor("Red");
        tShirt2.setDescription("100% cotton t-shirt made in USA");
        tShirt2.setPrice(BigDecimal.valueOf(25.99));
        tShirt2.setQuantity(6);
        tShirt2 = tShirtRepository.save(tShirt2);
    }

    // Create tshirt
    @Test
    void createTShirt(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("Medium");
        tShirt.setColor("Yellow");
        tShirt.setDescription("50% cotton t-shirt made in USA");
        tShirt.setPrice(BigDecimal.valueOf(15.99));
        tShirt.setQuantity(10);

        assertEquals(2, tShirtRepository.findAll().size());
        tShirt = tShirtRepository.save(tShirt);
        assertEquals(3, tShirtRepository.findAll().size());
    }

    // Get tshirts by id
    @Test
    void findTShirtsById(){
        Optional<TShirt> newTShirt = tShirtRepository.findById(tShirt1.gettShirtId());
        assertEquals(newTShirt.get(), tShirt1);
    }

    // Get all tshirts
    @Test
    void findAllTShirts(){
        List<TShirt> tShirts = tShirtRepository.findAll();
        assertEquals(2, tShirts.size());
    }

    // Update tshirt
    @Test
    void updateTShirt(){
        tShirt1.setDescription("UPDATED");

        tShirtRepository.save(tShirt1);

        Optional<TShirt> tShirt = tShirtRepository.findById(tShirt1.gettShirtId());
        assertEquals(tShirt.get(), tShirt1);
    }

    // Delete tshirt
    @Test
    void deleteTShirtById(){
        tShirtRepository.deleteById(tShirt1.gettShirtId());

        Optional<TShirt> tShirt = tShirtRepository.findById(tShirt1.gettShirtId());
        assertFalse(tShirt.isPresent());
    }

    // Get tshirt by color
    @Test
    void findByColor(){
        List<TShirt> tShirtList = tShirtRepository.findByColor(tShirt1.getColor());
        assertEquals(1, tShirtList.size());
        assertEquals("Blue", tShirtList.get(0).getColor());
    }

    // Get tshirt by size
    @Test
    void findBySize(){
        List<TShirt> tShirtList = tShirtRepository.findBySize(tShirt1.getSize());
        assertEquals(1, tShirtList.size());
        assertEquals("Small", tShirtList.get(0).getSize());
    }

}
