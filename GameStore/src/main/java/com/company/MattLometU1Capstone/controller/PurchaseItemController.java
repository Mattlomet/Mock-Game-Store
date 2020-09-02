package com.company.MattLometU1Capstone.controller;


import com.company.MattLometU1Capstone.ViewModel.InvoiceViewModel;
import com.company.MattLometU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PurchaseItemController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel purchaseItem(@RequestBody @Valid InvoiceViewModel ivm){
        return serviceLayer.createInvoice(ivm);
    }



}
