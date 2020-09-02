package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.Console;
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
public class ConsoleDaoTest {

    @Autowired
    ConsoleDao consoleDao;

    @Before
    public void setUp(){
        List<Console> consoleList = consoleDao.getAllConsoles();
        consoleList.stream()
                .forEach(console -> consoleDao.deleteConsole(console.getConsole_id()));
    }

    @Test
    public void addGetGetAllDeleteConsoleTest(){
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("manufacturer");
        console.setMemory_amount("1000");
        console.setProcessor("1000");
        console.setPrice(new BigDecimal("14.99"));
        console.setQuantity(100);

        console = consoleDao.addConsole(console);

        Console console2 = consoleDao.getConsole(console.getConsole_id());

        assertEquals(console,console2);

        consoleDao.deleteConsole(console.getConsole_id());

        assertEquals(0, consoleDao.getAllConsoles().size());

    }

    @Test
    public void updateConsoleTest(){
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("manufacturer");
        console.setMemory_amount("1000");
        console.setProcessor("1000");
        console.setPrice(new BigDecimal("14.99"));
        console.setQuantity(100);
        console = consoleDao.addConsole(console);

        console.setPrice(new BigDecimal("20.95"));
        consoleDao.updateConsole(console);

        assertEquals(new BigDecimal("20.95"), consoleDao.getConsole(console.getConsole_id()).getPrice());
    }

    @Test
    public void selectByManufacturerTest(){
        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("manufacturer");
        console.setMemory_amount("1000");
        console.setProcessor("1000");
        console.setPrice(new BigDecimal("14.99"));
        console.setQuantity(100);
        console = consoleDao.addConsole(console);

        assertEquals(console, consoleDao.getConsoleByManufacturer("manufacturer").get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgWhenIdIsInvalid(){
        consoleDao.getConsole(1000);
    }

    @Test
    public void shouldReturnEmptyArrayWhenManufacturerIsInvalid(){
        assertEquals(new ArrayList<>(),consoleDao.getConsoleByManufacturer("Doesn't Exist"));
    }
}
