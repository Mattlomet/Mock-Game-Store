package com.company.MattLometU1Capstone.service;

import com.company.MattLometU1Capstone.ViewModel.ConsoleViewModel;
import com.company.MattLometU1Capstone.ViewModel.GameViewModel;
import com.company.MattLometU1Capstone.ViewModel.InvoiceViewModel;
import com.company.MattLometU1Capstone.ViewModel.TShirtViewModel;
import com.company.MattLometU1Capstone.dao.*;
import com.company.MattLometU1Capstone.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    GameDao gameDao;
    ConsoleDao consoleDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    SalesTaxRateDao salesTaxRateDao;
    ProccessingFeeDao proccessingFeeDao;

    @Autowired
    public ServiceLayer(GameDao gameDao,ConsoleDao consoleDao,TShirtDao tShirtDao, InvoiceDao invoiceDao, SalesTaxRateDao salesTaxRateDao, ProccessingFeeDao proccessingFeeDao){
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tShirtDao = tShirtDao;
        this.invoiceDao = invoiceDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.proccessingFeeDao = proccessingFeeDao;
    }

    @Transactional
    public InvoiceViewModel createInvoice(InvoiceViewModel ivm){

        // get sales rate throw error if it cant find match
        SalesTaxRate salesTaxRate = salesTaxRateDao.getSalesTaxRate(ivm.getState());

        // get processing fee throw error if it cant find match
        ProcessingFee processingFee = proccessingFeeDao.getProcessingFee(ivm.getItem_type());

        // switch statement to get unit price
        // 1. cases for each item type -- error if it doesn't match a case
        // 2. get Object with id -- error if it doesn't find a result
        // 3. make sure quantity of order doesn't exceed quantity of item in DB
        BigDecimal unitPrice;

        switch (ivm.getItem_type()){
            case "Consoles":
                Console console = consoleDao.getConsole(ivm.getItem_id());
                if (ivm.getQuantity() > console.getQuantity()) {
                    throw new IllegalArgumentException("Quantity entered is too high");
                }else{
                    console.setQuantity(console.getQuantity()-ivm.getQuantity());
                    consoleDao.updateConsole(console);
                }
                unitPrice = console.getPrice();
                break;
            case "Games":
                Game game = gameDao.getGame(ivm.getItem_id());
                if (ivm.getQuantity() > game.getQuantity()) {
                    throw new IllegalArgumentException("Quantity entered is too high");
                }else{
                    game.setQuantity(game.getQuantity()-ivm.getQuantity());
                    gameDao.updateGame(game);
                }
                unitPrice = game.getPrice();
                break;
            case "T-Shirts":
                TShirt tShirt = tShirtDao.getTShirt(ivm.getItem_id());
                if (ivm.getQuantity() > tShirt.getQuantity()) {
                    throw new IllegalArgumentException("Quantity entered is too high");
                }else{
                    tShirt.setQuantity(tShirt.getQuantity()-ivm.getQuantity());
                    tShirtDao.updateTShirt(tShirt);
                }
                unitPrice = tShirt.getPrice();
                break;
            default:
                throw new IllegalArgumentException("Must enter a valid product type.");
        }

        // convert quantity to big dec
        BigDecimal quantityBigDec = BigDecimal.valueOf(ivm.getQuantity());
        // quantity times unit price
        BigDecimal quantityTimesUnitPrice = quantityBigDec.multiply(unitPrice);
        // sales tax times (unit*quantity)
        BigDecimal salesTaxAdded = quantityTimesUnitPrice.multiply(salesTaxRate.getRate());
        // (unit*quantity)+salesTax
        BigDecimal subtotal = salesTaxAdded.add(quantityTimesUnitPrice).setScale(2, RoundingMode.HALF_UP);

        // calculate total subtotal+processing fee
        BigDecimal total;
        BigDecimal processingFeeSave;

        // add extra processing fee if order quantity > 10
        if (ivm.getQuantity() >= 10){
            BigDecimal extra = new BigDecimal("15.49");
            processingFeeSave = processingFee.getFee().add(extra);
            total = subtotal.add(processingFeeSave);
        }else{
            processingFeeSave = processingFee.getFee();
            total = subtotal.add(processingFeeSave);
        }

        // if order total > 999.99 throw error
        if (total.compareTo(new BigDecimal("999.99")) == 1){
            throw new IllegalArgumentException("The total order value is too high ($999.99 per invoice allowed)");
        }

        // build invoice object to send to db
        Invoice invoice = new Invoice();
        invoice.setName(ivm.getName());
        invoice.setStreet(ivm.getStreet());
        invoice.setCity(ivm.getCity());
        invoice.setState(ivm.getState());
        invoice.setZipcode(ivm.getZipcode());
        invoice.setItem_type(ivm.getItem_type());
        invoice.setItem_id(ivm.getItem_id());
        invoice.setUnit_price(unitPrice);
        invoice.setQuantity(ivm.getQuantity());
        invoice.setSubtotal(subtotal);
        invoice.setTax(salesTaxAdded.setScale(2));
        invoice.setProcessing_fee(processingFeeSave);
        invoice.setTotal(total.setScale(2,RoundingMode.HALF_UP));
        invoice = invoiceDao.addInvoice(invoice);

        // send to buildInvoice
        return buildInvoiceViewModel(invoice);

    }

    public InvoiceViewModel getInvoiceById(int id){
        Invoice invoice = invoiceDao.getInvoice(id);
        return buildInvoiceViewModel(invoice);
    }





    //Console API
    public ConsoleViewModel createConsole(ConsoleViewModel cvm){
        Console console = new Console();
        console.setModel(cvm.getModel());
        console.setManufacturer(cvm.getManufacturer());
        console.setMemory_amount(cvm.getMemory_amount());
        console.setProcessor(cvm.getProcessor());
        console.setPrice(cvm.getPrice());
        console.setQuantity(cvm.getQuantity());
        console = consoleDao.addConsole(console);
        return buildConsoleViewModel(console);
    }

    public ConsoleViewModel getConsoleById(int id){
        Console console = consoleDao.getConsole(id);
        return buildConsoleViewModel(console);
    }

    public void updateConsole(ConsoleViewModel cvm){
        Console console = new Console();
        console.setConsole_id(cvm.getConsole_id());
        console.setModel(cvm.getModel());
        console.setManufacturer(cvm.getManufacturer());
        console.setMemory_amount(cvm.getMemory_amount());
        console.setProcessor(cvm.getProcessor());
        console.setPrice(cvm.getPrice());
        console.setQuantity(cvm.getQuantity());
        consoleDao.updateConsole(console);
    }

    public void deleteConsole(int id){
        consoleDao.deleteConsole(id);
    }

    public List<ConsoleViewModel> getConsoleByManufacturer(String manufacturer){
        List<Console> consoleList = consoleDao.getConsoleByManufacturer(manufacturer);
        List<ConsoleViewModel> consoleViewModelList = new ArrayList();
        for (Console console:
             consoleList) {
            consoleViewModelList.add(buildConsoleViewModel(console));
        }
        return consoleViewModelList;
    }


    //Game API
    public GameViewModel createGame(GameViewModel gvm){
        Game game = new Game();
        game.setTitle(gvm.getTitle());
        game.setEsrb_rating(gvm.getEsrb_rating());
        game.setDescription(gvm.getDescription());
        game.setPrice(gvm.getPrice());
        game.setStudio(gvm.getStudio());
        game.setQuantity(gvm.getQuantity());
        game = gameDao.addGame(game);
        return buildGameViewModel(game);
    }
    public GameViewModel getGameById(int id){
        Game game = gameDao.getGame(id);
        return buildGameViewModel(game);
    }
    public void updateGame(GameViewModel gvm){
        Game game = new Game();
        game.setGame_id(gvm.getGame_id());
        game.setTitle(gvm.getTitle());
        game.setEsrb_rating(gvm.getEsrb_rating());
        game.setDescription(gvm.getDescription());
        game.setPrice(gvm.getPrice());
        game.setStudio(gvm.getStudio());
        game.setQuantity(gvm.getQuantity());
        gameDao.updateGame(game);
    }
    public void deleteGame(int id){
        gameDao.deleteGame(id);
    }
    public List<GameViewModel> getGameByStudio(String studio){
        List<Game> gameList = gameDao.getGameByStudio(studio);
        List<GameViewModel> gameViewModelList = new ArrayList<>();
        for (Game game:
             gameList) {
            gameViewModelList.add(buildGameViewModel(game));
        }
        return gameViewModelList;
    }
    public List<GameViewModel> getGameByEsrb(String esrb){
        List<Game> gameList = gameDao.getGameByESRB(esrb);
        List<GameViewModel> gameViewModelList = new ArrayList<>();
        for (Game game:
                gameList) {
            gameViewModelList.add(buildGameViewModel(game));
        }
        return gameViewModelList;
    }
    public List<GameViewModel> getGameByTitle(String title){
        List<Game> gameList = gameDao.getGameByTitle(title);
        List<GameViewModel> gameViewModelList = new ArrayList<>();
        for (Game game:
                gameList) {
            gameViewModelList.add(buildGameViewModel(game));
        }
        return gameViewModelList;
    }

    //TShirt API

    public TShirtViewModel createTShirt(TShirtViewModel tsvm){
        TShirt tShirt = new TShirt();
        tShirt.setSize(tsvm.getSize());
        tShirt.setColor(tsvm.getColor());
        tShirt.setDescription(tsvm.getDescription());
        tShirt.setPrice(tsvm.getPrice());
        tShirt.setQuantity(tsvm.getQuantity());
        tShirt = tShirtDao.addTShirt(tShirt);
        return buildTShirtViewModel(tShirt);
    }

    public TShirtViewModel getTShirtById(int id){
        TShirt tShirt = tShirtDao.getTShirt(id);
        return buildTShirtViewModel(tShirt);
    }

    public void updateTShirt(TShirtViewModel tsvm){
        TShirt tShirt = new TShirt();
        tShirt.setT_shirt_id(tsvm.getT_shirt_id());
        tShirt.setSize(tsvm.getSize());
        tShirt.setColor(tsvm.getColor());
        tShirt.setDescription(tsvm.getDescription());
        tShirt.setPrice(tsvm.getPrice());
        tShirt.setQuantity(tsvm.getQuantity());
        tShirtDao.updateTShirt(tShirt);
    }

    public void deleteTShirt(int id){
        tShirtDao.deleteTShirt(id);
    }

    public List<TShirtViewModel> getTShirtBySize(String size){
        List<TShirt> tShirtList = tShirtDao.getTShirtBySize(size);
        List<TShirtViewModel> tShirtViewModelList = new ArrayList<>();
        for (TShirt tshirt:
             tShirtList) {
            tShirtViewModelList.add(buildTShirtViewModel(tshirt));
        }
        return tShirtViewModelList;
    }

    public List<TShirtViewModel> getTShirtByColor(String color){
        List<TShirt> tShirtList = tShirtDao.getTShirtByColor(color);
        List<TShirtViewModel> tShirtViewModelList = new ArrayList<>();
        for (TShirt tshirt:
                tShirtList) {
            tShirtViewModelList.add(buildTShirtViewModel(tshirt));
        }
        return tShirtViewModelList;
    }


    //Builder Methods
    private GameViewModel buildGameViewModel(Game game) {

        GameViewModel gvm = new GameViewModel();
        gvm.setGame_id(game.getGame_id());
        gvm.setTitle(game.getTitle());
        gvm.setEsrb_rating(game.getEsrb_rating());
        gvm.setDescription(game.getDescription());
        gvm.setPrice(game.getPrice());
        gvm.setStudio(game.getStudio());
        gvm.setQuantity(game.getQuantity());

        return gvm;

    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {

        ConsoleViewModel cvm = new ConsoleViewModel();
        cvm.setConsole_id(console.getConsole_id());
        cvm.setModel(console.getModel());
        cvm.setManufacturer(console.getManufacturer());
        cvm.setMemory_amount(console.getMemory_amount());
        cvm.setProcessor(console.getProcessor());
        cvm.setPrice(console.getPrice());
        cvm.setQuantity(console.getQuantity());
        return cvm;

    }

    private TShirtViewModel buildTShirtViewModel(TShirt tShirt) {

        TShirtViewModel tsvm = new TShirtViewModel();
        tsvm.setT_shirt_id(tShirt.getT_shirt_id());
        tsvm.setSize(tShirt.getSize());
        tsvm.setColor(tShirt.getColor());
        tsvm.setDescription(tShirt.getDescription());
        tsvm.setPrice(tShirt.getPrice());
        tsvm.setQuantity(tShirt.getQuantity());
        return tsvm;

    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setInvoice_id(invoice.getInvoice_id());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipcode(invoice.getZipcode());
        ivm.setItem_type(invoice.getItem_type());
        ivm.setItem_id(invoice.getItem_id());
        ivm.setUnit_price(invoice.getUnit_price());
        ivm.setQuantity(invoice.getQuantity());
        ivm.setSubtotal(invoice.getSubtotal());
        ivm.setTax(invoice.getTax());
        ivm.setProcessing_fee(invoice.getProcessing_fee());
        ivm.setTotal(invoice.getTotal());
        return ivm;
    }
}
