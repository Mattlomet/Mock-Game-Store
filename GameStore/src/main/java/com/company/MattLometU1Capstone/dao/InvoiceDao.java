package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice Invoice);
    Invoice getInvoice(int id);
    List<Invoice> getAllInvoices();
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
}
