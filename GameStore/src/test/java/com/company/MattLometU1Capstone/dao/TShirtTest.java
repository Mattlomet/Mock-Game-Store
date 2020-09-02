package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtTest {

    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void setUp(){
        List<TShirt> tShirtList = tShirtDao.getAllTShirts();
        tShirtList.stream()
                .forEach(tShirt -> tShirtDao.deleteTShirt(tShirt.getT_shirt_id()));
    }

    @Test
    public void addGetGetAllDeleteTShirtTest(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("size");
        tShirt.setColor("color");
        tShirt.setDescription("description");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(100);
        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt2 = tShirtDao.getTShirt(tShirt.getT_shirt_id());
        assertEquals(tShirt,tShirt2);

        tShirtDao.deleteTShirt(tShirt.getT_shirt_id());
        assertEquals(0,tShirtDao.getAllTShirts().size());

    }

    @Test
    public void updateTShirtTest(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("size");
        tShirt.setColor("color");
        tShirt.setDescription("description");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(100);
        tShirt = tShirtDao.addTShirt(tShirt);

        tShirt.setColor("new color");
        tShirtDao.updateTShirt(tShirt);

        assertEquals("new color", tShirtDao.getAllTShirts().get(0).getColor());

    }

    @Test
    public void getByColorTest(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("size");
        tShirt.setColor("color");
        tShirt.setDescription("description");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(100);
        tShirt = tShirtDao.addTShirt(tShirt);

        assertEquals(tShirt, tShirtDao.getTShirtByColor("color").get(0));
    }

    @Test
    public void getBySizeTest(){
        TShirt tShirt = new TShirt();
        tShirt.setSize("size");
        tShirt.setColor("color");
        tShirt.setDescription("description");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(100);
        tShirt = tShirtDao.addTShirt(tShirt);

        assertEquals(tShirt,tShirtDao.getTShirtBySize("size").get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgWhenIdDoesNotExist(){
        tShirtDao.getTShirt(1000);
    }

    @Test
    public void shouldReturnEmptyArrayWhenSizeDoesNotExist(){
        assertEquals(new ArrayList<>(),tShirtDao.getTShirtBySize("super duper extra large"));
    }

    @Test
    public void shouldReturnEmptyArrayWhenColorDoesNotExist(){
        assertEquals(new ArrayList<>(),tShirtDao.getTShirtByColor("rainbow"));
    }


}
