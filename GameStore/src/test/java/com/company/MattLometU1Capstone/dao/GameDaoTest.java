package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.Console;
import com.company.MattLometU1Capstone.models.Game;
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
public class GameDaoTest {

    @Autowired
    GameDao gameDao;

    @Before
    public void setUp(){
        List<Game> gameList = gameDao.getAllGames();
        gameList.stream()
                .forEach(game -> gameDao.deleteGame(game.getGame_id()));
    }

    @Test
    public void addGetGetAllDeleteGameTest(){
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("esrb rating");
        game.setDescription("description");
        game.setPrice(new BigDecimal("20.00"));
        game.setStudio("studio");
        game.setQuantity(1000);
        game = gameDao.addGame(game);

        Game game2 = gameDao.getGame(game.getGame_id());
        assertEquals(game,game2);

        gameDao.deleteGame(game.getGame_id());
        assertEquals(0,gameDao.getAllGames().size());
    }

    @Test
    public void updateGameTest(){
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("esrb rating");
        game.setDescription("description");
        game.setPrice(new BigDecimal("20.00"));
        game.setStudio("studio");
        game.setQuantity(1000);
        game = gameDao.addGame(game);

        game.setStudio("new studio");
        gameDao.updateGame(game);

        assertEquals("new studio",gameDao.getGame(game.getGame_id()).getStudio());
    }

    @Test
    public void getGameByStudioTest(){
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("esrb rating");
        game.setDescription("description");
        game.setPrice(new BigDecimal("20.00"));
        game.setStudio("studio");
        game.setQuantity(1000);
        game = gameDao.addGame(game);

        assertEquals(game,gameDao.getGameByStudio("studio").get(0));
    }

    @Test
    public void getGameByESRBTest(){
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("esrb rating");
        game.setDescription("description");
        game.setPrice(new BigDecimal("20.00"));
        game.setStudio("studio");
        game.setQuantity(1000);
        game = gameDao.addGame(game);

        assertEquals(game,gameDao.getGameByESRB("esrb rating").get(0));
    }

    @Test
    public void getGameByTitleTest(){
        Game game = new Game();
        game.setTitle("title");
        game.setEsrb_rating("esrb rating");
        game.setDescription("description");
        game.setPrice(new BigDecimal("20.00"));
        game.setStudio("studio");
        game.setQuantity(1000);
        game = gameDao.addGame(game);

        assertEquals(game,gameDao.getGameByTitle("title").get(0));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgWhenIdDoesNotExist(){
        gameDao.getGame(1000);
    }

    @Test
    public void shouldReturnEmptyArrayWhenStudioDoesNotExist(){
        assertEquals(new ArrayList<>(),gameDao.getGameByStudio("Blah Blah Blah"));
    }

    @Test
    public void shouldReturnEmptyArrayWhenESRBDoesNotExist(){
        assertEquals(new ArrayList<>(),gameDao.getGameByESRB("Blah"));
    }

    @Test
    public void shouldReturnEmptyArrayWhenTitleDoesNotExist(){
        assertEquals(new ArrayList<>(),gameDao.getGameByTitle("Blah Blah"));
    }
}
