package com.company.MattLometU1Capstone.controller;


import com.company.MattLometU1Capstone.ViewModel.TShirtViewModel;
import com.company.MattLometU1Capstone.models.TShirt;
import com.company.MattLometU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TShirtController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTshirt(@RequestBody @Valid TShirtViewModel tShirt){
        return serviceLayer.createTShirt(tShirt);
    }

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTshirtById(@PathVariable int id){
        return serviceLayer.getTShirtById(id);
    }


    @PutMapping("/tshirt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid TShirtViewModel tShirt){
        serviceLayer.updateTShirt(tShirt);
    }


    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id){
        serviceLayer.deleteTShirt(id);
    }


    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTshirtBySize(@PathVariable String size){
        return serviceLayer.getTShirtBySize(size);
    }


    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTshirtByColor(@PathVariable String color){
        return  serviceLayer.getTShirtByColor(color);
    }

}
