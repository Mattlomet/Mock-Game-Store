package com.company.MattLometU1Capstone.controller;


import com.company.MattLometU1Capstone.ViewModel.ConsoleViewModel;
import com.company.MattLometU1Capstone.models.Console;
import com.company.MattLometU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel console){
        return serviceLayer.createConsole(console);
    }

    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsoleById(@PathVariable int id){
        return serviceLayer.getConsoleById(id);
    }

    @PutMapping("/console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid ConsoleViewModel console){
        serviceLayer.updateConsole(console);
    }

    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id){
        serviceLayer.deleteConsole(id);
    }


    @GetMapping("/manufacturer/{manu}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable String manu){
        return serviceLayer.getConsoleByManufacturer(manu);
    }

}
