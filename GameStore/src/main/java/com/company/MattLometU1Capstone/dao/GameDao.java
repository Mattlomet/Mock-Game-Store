package com.company.MattLometU1Capstone.dao;

import com.company.MattLometU1Capstone.models.Game;

import java.util.List;

public interface GameDao {
    Game addGame(Game game);
    Game getGame(int id);
    List<Game> getAllGames();
    void updateGame(Game game);
    void deleteGame(int id);
    List<Game> getGameByStudio(String studio);
    List<Game> getGameByESRB(String esrb);
    List<Game> getGameByTitle(String title);
}
