package com.company.MattLometU1Capstone.controller;

import com.company.MattLometU1Capstone.ViewModel.GameViewModel;
import com.company.MattLometU1Capstone.models.Game;
import com.company.MattLometU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game){
        GameViewModel gvm = serviceLayer.createGame(game);
       return gvm;
    }

    @GetMapping("/game/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGameById(@PathVariable int id){
        return serviceLayer.getGameById(id);
    }


    @PutMapping("/game")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid GameViewModel game){
        serviceLayer.updateGame(game);
    }


    @DeleteMapping("/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id){
        serviceLayer.deleteGame(id);
    }

    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByStudio(@PathVariable String studio){
        return serviceLayer.getGameByStudio(studio);
    }

    @GetMapping("/esrb/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByEsrb(@PathVariable String esrb){
        return serviceLayer.getGameByEsrb(esrb);
    }


    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByTitle(@PathVariable String title){
        return serviceLayer.getGameByTitle(title);
    }


}


