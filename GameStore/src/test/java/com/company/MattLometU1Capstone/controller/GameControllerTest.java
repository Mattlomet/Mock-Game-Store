package com.company.MattLometU1Capstone.controller;


import com.company.MattLometU1Capstone.ViewModel.GameViewModel;
import com.company.MattLometU1Capstone.dao.GameDao;
import com.company.MattLometU1Capstone.models.Game;
import com.company.MattLometU1Capstone.service.ServiceLayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest{


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    GameViewModel gameViewModel = new GameViewModel();
    GameViewModel gameViewModel1 = new GameViewModel();
    GameViewModel gameViewModelMalFormed = new GameViewModel();



    @Before
    public void setUp(){
        gameViewModel.setTitle("title");
        gameViewModel.setEsrb_rating("esrbrating");
        gameViewModel.setDescription("description");
        gameViewModel.setPrice(new BigDecimal("20.00"));
        gameViewModel.setStudio("studio");
        gameViewModel.setQuantity(1000);

        gameViewModel1.setTitle("title");
        gameViewModel1.setEsrb_rating("esrbrating");
        gameViewModel1.setDescription("description");
        gameViewModel1.setPrice(new BigDecimal("20.00"));
        gameViewModel1.setStudio("studio");
        gameViewModel1.setQuantity(1000);
        gameViewModel1.setGame_id(17);


        gameViewModelMalFormed.setTitle("");
        gameViewModelMalFormed.setEsrb_rating("esrbrating");
        gameViewModelMalFormed.setDescription("description");
        gameViewModelMalFormed.setPrice(new BigDecimal("20.00"));
        gameViewModelMalFormed.setStudio("studio");
        gameViewModelMalFormed.setQuantity(1000);

        setUpServiceMocks();
    }

    @Test
    public void createGameShouldReturnGameViewModel() throws Exception {

        String inputJson = mapper.writeValueAsString(gameViewModel);

        String outJson = mapper.writeValueAsString(gameViewModel1);

        this.mockMvc.perform(post("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outJson));
    }

    @Test
    public void shouldReturnGVMWhenGetById() throws Exception {
        String outJson = mapper.writeValueAsString(gameViewModel1);

        this.mockMvc.perform(get("/game/17"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(outJson));
    }

    @Test
    public void shouldReturnGVMWhenGetByEsrb() throws Exception {
        List<GameViewModel> gameViewModelList = new ArrayList<>();
        gameViewModelList.add(gameViewModel1);

        String outJson = mapper.writeValueAsString(gameViewModelList);

        this.mockMvc.perform(get("/esrb/esrbrating"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(outJson));
    }

    @Test
    public void shouldReturnGVMWhenGetByTitle() throws Exception {
        List<GameViewModel> gameViewModelList = new ArrayList<>();
        gameViewModelList.add(gameViewModel1);

        String outJson = mapper.writeValueAsString(gameViewModelList);

        this.mockMvc.perform(get("/title/title"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(outJson));
    }

    @Test
    public void shouldReturnGVMWhenGetByStudio() throws Exception {
        List<GameViewModel> gameViewModelList = new ArrayList<>();
        gameViewModelList.add(gameViewModel1);

        String outJson = mapper.writeValueAsString(gameViewModelList);

        this.mockMvc.perform(get("/studio/studio"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(outJson));
    }

    @Test
    public void shouldThrowUnprocessEntityWhenGVMisMalformed() throws Exception {
        String inputJson = mapper.writeValueAsString(gameViewModelMalFormed);
        this.mockMvc.perform(post("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnprocessableEntity());

    }

    public void setUpServiceMocks()  {

        List<GameViewModel> gameViewModelList = new ArrayList<>();
        gameViewModelList.add(gameViewModel1);

        doReturn(gameViewModel1).when(serviceLayer).createGame(gameViewModel);
        doReturn(gameViewModel1).when(serviceLayer).getGameById(gameViewModel1.getGame_id());
        doReturn(gameViewModelList).when(serviceLayer).getGameByEsrb("esrbrating");
        doReturn(gameViewModelList).when(serviceLayer).getGameByStudio("studio");
        doReturn(gameViewModelList).when(serviceLayer).getGameByTitle("title");


        doThrow(IllegalArgumentException.class).when(serviceLayer).createGame(gameViewModelMalFormed);

    }
}
