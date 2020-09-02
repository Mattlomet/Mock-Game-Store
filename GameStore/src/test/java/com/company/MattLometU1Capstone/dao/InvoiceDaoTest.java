package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.Game;
import com.company.MattLometU1Capstone.models.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp(){
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        for (Invoice invoice:
             invoiceList) {
            invoiceDao.deleteInvoice(invoice.getInvoice_id());
        }
    }

    @Test
    public void getGetAllDeleteInvoiceTest(){

        Invoice invoice = new Invoice();
        invoice.setName("name");
        invoice.setStreet("street");
        invoice.setCity("city");
        invoice.setState("state");
        invoice.setZipcode("07719");
        invoice.setItem_type("type");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("14.99"));
        invoice.setQuantity(100);
        invoice.setSubtotal(new BigDecimal("14.99"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessing_fee(new BigDecimal("1.00"));
        invoice.setTotal(new BigDecimal("16.99"));

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertEquals(invoice,invoice1);

        invoiceDao.deleteInvoice(invoice.getInvoice_id());

        assertEquals(0, invoiceDao.getAllInvoices().size());

    }

    @Test(expected = DataIntegrityViolationException .class)
    public void shouldThrowDataIntegrityArgWhenTotalIsTooHigh(){
        Invoice invoice = new Invoice();
        invoice.setName("name");
        invoice.setStreet("street");
        invoice.setCity("city");
        invoice.setState("state");
        invoice.setZipcode("07719");
        invoice.setItem_type("type");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("14.99"));
        invoice.setQuantity(100);
        invoice.setSubtotal(new BigDecimal("14.99"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessing_fee(new BigDecimal("1.00"));
        invoice.setTotal(new BigDecimal("1000.00"));

        invoiceDao.addInvoice(invoice);
    }


    @Test
    public void updateInvoice(){

        Invoice invoice = new Invoice();
        invoice.setName("name");
        invoice.setStreet("street");
        invoice.setCity("city");
        invoice.setState("state");
        invoice.setZipcode("07719");
        invoice.setItem_type("type");
        invoice.setItem_id(1);
        invoice.setUnit_price(new BigDecimal("14.99"));
        invoice.setQuantity(100);
        invoice.setSubtotal(new BigDecimal("14.99"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessing_fee(new BigDecimal("1.00"));
        invoice.setTotal(new BigDecimal("16.99"));

        invoice = invoiceDao.addInvoice(invoice);

        invoice.setZipcode("08888");

        invoiceDao.updateInvoice(invoice);

        assertEquals("08888",invoiceDao.getInvoice(invoice.getInvoice_id()).getZipcode());
    }

}
