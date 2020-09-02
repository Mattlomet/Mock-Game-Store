package com.company.MattLometU1Capstone.service;

import com.company.MattLometU1Capstone.ViewModel.ConsoleViewModel;
import com.company.MattLometU1Capstone.ViewModel.GameViewModel;
import com.company.MattLometU1Capstone.ViewModel.InvoiceViewModel;
import com.company.MattLometU1Capstone.ViewModel.TShirtViewModel;
import com.company.MattLometU1Capstone.dao.*;
import com.company.MattLometU1Capstone.models.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;
    GameDao gameDao;
    ConsoleDao consoleDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    ProccessingFeeDao proccessingFeeDao;
    SalesTaxRateDao salesTaxRateDao;

    @Before
    public void setUp(){
        setUpGameMocks();
        setUpConsoleMocks();
        setUpTShirtMocks();
        setUpInvoiceMocks();
        serviceLayer = new ServiceLayer(gameDao,consoleDao,tShirtDao,invoiceDao,salesTaxRateDao,proccessingFeeDao);
    }


    // GAME tests
    @Test
    public void addGetGame(){
        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("title");
        gvm.setEsrb_rating("esrb rating");
        gvm.setDescription("description");
        gvm.setPrice(new BigDecimal("20.00").setScale(2));
        gvm.setStudio("studio");
        gvm.setQuantity(1000);

        gvm = serviceLayer.createGame(gvm);
        GameViewModel gvmFromService = serviceLayer.getGameById(gvm.getGame_id());
        assertEquals(gvm,gvmFromService);
    }

    @Test
    public void getGameByEsrb(){
        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("title");
        gvm.setEsrb_rating("esrb rating");
        gvm.setDescription("description");
        gvm.setPrice(new BigDecimal("20.00").setScale(2));
        gvm.setStudio("studio");
        gvm.setQuantity(1000);

        gvm = serviceLayer.createGame(gvm);
        assertEquals(gvm, serviceLayer.getGameByEsrb(gvm.getEsrb_rating()).get(0));
    }

    @Test
    public void getGameByStudio(){
        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("title");
        gvm.setEsrb_rating("esrb rating");
        gvm.setDescription("description");
        gvm.setPrice(new BigDecimal("20.00").setScale(2));
        gvm.setStudio("studio");
        gvm.setQuantity(1000);

        gvm = serviceLayer.createGame(gvm);
        assertEquals(gvm, serviceLayer.getGameByStudio(gvm.getStudio()).get(0));
    }

    @Test
    public void getGameByTitle(){
        GameViewModel gvm = new GameViewModel();
        gvm.setTitle("title");
        gvm.setEsrb_rating("esrb rating");
        gvm.setDescription("description");
        gvm.setPrice(new BigDecimal("20.00").setScale(2));
        gvm.setStudio("studio");
        gvm.setQuantity(1000);

        gvm = serviceLayer.createGame(gvm);
        assertEquals(gvm, serviceLayer.getGameByTitle(gvm.getTitle()).get(0));
    }




    // CONSOLE test

    @Test
    public void addGetConsole(){
        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setModel("model");
        cvm.setManufacturer("manufacturer");
        cvm.setMemory_amount("1000");
        cvm.setProcessor("1000");
        cvm.setPrice(new BigDecimal("14.99"));
        cvm.setQuantity(100);

        cvm = serviceLayer.createConsole(cvm);
        assertEquals(cvm,serviceLayer.getConsoleById(cvm.getConsole_id()));
    }

    @Test
    public void getConsoleByManufacturer(){
        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setModel("model");
        cvm.setManufacturer("manufacturer");
        cvm.setMemory_amount("1000");
        cvm.setProcessor("1000");
        cvm.setPrice(new BigDecimal("14.99"));
        cvm.setQuantity(100);

        cvm = serviceLayer.createConsole(cvm);
        assertEquals(cvm,serviceLayer.getConsoleByManufacturer(cvm.getManufacturer()).get(0));
    }


    // TSHIRT test

    @Test
    public void addGetTShirt(){
        TShirtViewModel tvm = new TShirtViewModel();
        tvm.setSize("size");
        tvm.setColor("color");
        tvm.setDescription("description");
        tvm.setPrice(new BigDecimal("20.00"));
        tvm.setQuantity(100);

        tvm = serviceLayer.createTShirt(tvm);
        assertEquals(tvm, serviceLayer.getTShirtById(tvm.getT_shirt_id()));
    }

    @Test
    public void getTShirtByColor(){
        TShirtViewModel tvm = new TShirtViewModel();
        tvm.setSize("size");
        tvm.setColor("color");
        tvm.setDescription("description");
        tvm.setPrice(new BigDecimal("20.00"));
        tvm.setQuantity(100);

        tvm = serviceLayer.createTShirt(tvm);

        assertEquals(tvm, serviceLayer.getTShirtByColor(tvm.getColor()).get(0));
    }

    @Test
    public void getTShirtBySize(){
        TShirtViewModel tvm = new TShirtViewModel();
        tvm.setSize("size");
        tvm.setColor("color");
        tvm.setDescription("description");
        tvm.setPrice(new BigDecimal("20.00"));
        tvm.setQuantity(100);

        tvm = serviceLayer.createTShirt(tvm);
        assertEquals(tvm, serviceLayer.getTShirtBySize(tvm.getSize()).get(0));
    }



    // INVOICE test

    @Test
    public void addGetInvoice(){
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setName("name");
        ivm.setStreet("street");
        ivm.setCity("city");
        ivm.setState("NJ");
        ivm.setZipcode("07719");
        ivm.setItem_type("Games");
        ivm.setItem_id(1);
        ivm.setUnit_price(new BigDecimal("20.00"));
        ivm.setQuantity(2);
        ivm.setSubtotal(new BigDecimal("42.00"));
        ivm.setTax(new BigDecimal("2.00"));
        ivm.setProcessing_fee(new BigDecimal("1.49"));
        ivm.setTotal(new BigDecimal("43.49"));


        ivm = serviceLayer.createInvoice(ivm);
        assertEquals(ivm,serviceLayer.getInvoiceById(ivm.getItem_id()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgWhenQuantityTooHigh(){
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setName("name");
        ivm.setStreet("street");
        ivm.setCity("city");
        ivm.setState("NJ");
        ivm.setZipcode("07719");
        ivm.setItem_type("Games");
        ivm.setItem_id(1);
        ivm.setUnit_price(new BigDecimal("20.00"));
        ivm.setQuantity(2000);
        ivm.setSubtotal(new BigDecimal("42.00"));
        ivm.setTax(new BigDecimal("2.00"));
        ivm.setProcessing_fee(new BigDecimal("1.49"));
        ivm.setTotal(new BigDecimal("1000.00"));

        serviceLayer.createInvoice(ivm);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgWhenItemTypeDoesNotExist(){
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setName("name");
        ivm.setStreet("street");
        ivm.setCity("city");
        ivm.setState("NJ");
        ivm.setZipcode("07719");
        ivm.setItem_type("DVD");
        ivm.setItem_id(1000);
        ivm.setUnit_price(new BigDecimal("20.00"));
        ivm.setQuantity(2000);
        ivm.setSubtotal(new BigDecimal("42.00"));
        ivm.setTax(new BigDecimal("2.00"));
        ivm.setProcessing_fee(new BigDecimal("1.49"));
        ivm.setTotal(new BigDecimal("1000.00"));

        serviceLayer.createInvoice(ivm);
    }



    public void setUpGameMocks(){

        gameDao = mock(GameDaoImpl.class);

        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("esrb rating");
        game.setDescription("description");
        game.setPrice(new BigDecimal("20.00").setScale(2));
        game.setStudio("studio");
        game.setQuantity(1000);

        Game game1 = new Game();
        game1.setGame_id(1);
        game1.setTitle("title");
        game1.setEsrb_rating("esrb rating");
        game1.setDescription("description");
        game1.setPrice(new BigDecimal("20.00").setScale(2));
        game1.setStudio("studio");
        game1.setQuantity(1000);


        List<Game> gameList = new ArrayList<>();
        gameList.add(game1);

        doReturn(game1).when(gameDao).addGame(game);
        doReturn(game1).when(gameDao).getGame(game1.getGame_id());
        doReturn(gameList).when(gameDao).getGameByESRB("esrb rating");
        doReturn(gameList).when(gameDao).getGameByStudio("studio");
        doReturn(gameList).when(gameDao).getGameByTitle("title");
    }

    public void setUpConsoleMocks(){

        consoleDao = mock(ConsoleDaoImpl.class);


        Console console = new Console();
        console.setModel("model");
        console.setManufacturer("manufacturer");
        console.setMemory_amount("1000");
        console.setProcessor("1000");
        console.setPrice(new BigDecimal("14.99"));
        console.setQuantity(100);

        Console console1 = new Console();
        console1.setConsole_id(1);
        console1.setModel("model");
        console1.setManufacturer("manufacturer");
        console1.setMemory_amount("1000");
        console1.setProcessor("1000");
        console1.setPrice(new BigDecimal("14.99"));
        console1.setQuantity(100);



        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);


        doReturn(console1).when(consoleDao).addConsole(console);
        doReturn(console1).when(consoleDao).getConsole(console1.getConsole_id());
        doReturn(consoleList).when(consoleDao).getConsoleByManufacturer("manufacturer");
    }

    public void setUpTShirtMocks(){

        tShirtDao = mock(TShirtDaoImpl.class);

        TShirt tShirt = new TShirt();
        tShirt.setSize("size");
        tShirt.setColor("color");
        tShirt.setDescription("description");
        tShirt.setPrice(new BigDecimal("20.00"));
        tShirt.setQuantity(100);


        TShirt tShirt1 = new TShirt();
        tShirt1.setT_shirt_id(1);
        tShirt1.setSize("size");
        tShirt1.setColor("color");
        tShirt1.setDescription("description");
        tShirt1.setPrice(new BigDecimal("20.00"));
        tShirt1.setQuantity(100);



        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt1);

        doReturn(tShirt1).when(tShirtDao).addTShirt(tShirt);
        doReturn(tShirt1).when(tShirtDao).getTShirt(tShirt1.getT_shirt_id());
        doReturn(tShirtList).when(tShirtDao).getTShirtByColor(tShirt1.getColor());
        doReturn(tShirtList).when(tShirtDao).getTShirtBySize(tShirt1.getSize());

    }

    public void setUpInvoiceMocks(){

        invoiceDao = mock(InvoiceDaoImpl.class);
        proccessingFeeDao = mock(ProcessingFeeDaoImpl.class);
        salesTaxRateDao = mock(SalesTaxRateDaoImpl.class);

        Invoice invoice = new Invoice();
        invoice.setName("name");
        invoice.setStreet("street");
        invoice.setCity("city");
        invoice.setState("NJ");
        invoice.setZipcode("07719");
        invoice.setItem_type("Games");
        invoice.setItem_id(1);
        invoice.setQuantity(2);
        invoice.setUnit_price(new BigDecimal("20.00"));
        invoice.setSubtotal(new BigDecimal("42.00"));
        invoice.setTax(new BigDecimal("2.00"));
        invoice.setProcessing_fee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("43.49"));


        Invoice invoice1 = new Invoice();
        invoice1.setInvoice_id(1);
        invoice1.setName("name");
        invoice1.setStreet("street");
        invoice1.setCity("city");
        invoice1.setState("NJ");
        invoice1.setZipcode("07719");
        invoice1.setItem_type("Games");
        invoice1.setItem_id(1);
        invoice1.setQuantity(2);
        invoice1.setUnit_price(new BigDecimal("20.00"));
        invoice1.setSubtotal(new BigDecimal("42.00"));
        invoice1.setTax(new BigDecimal("2.00"));
        invoice1.setProcessing_fee(new BigDecimal("1.49"));
        invoice1.setTotal(new BigDecimal("43.49"));


        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice1);

        doReturn(invoice1).when(invoiceDao).addInvoice(invoice);
        doReturn(invoice1).when(invoiceDao).getInvoice(invoice1.getInvoice_id());
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(".05"));

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProduct_type("Games");
        processingFee.setFee(new BigDecimal("1.49"));


        doReturn(salesTaxRate).when(salesTaxRateDao).getSalesTaxRate(invoice1.getState());

        doReturn(processingFee).when(proccessingFeeDao).getProcessingFee(invoice1.getItem_type());

    }
}
