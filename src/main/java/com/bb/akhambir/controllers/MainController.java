package com.bb.akhambir.controllers;

import com.bb.akhambir.driver.interfaces.GameProcessor;
import com.bb.akhambir.library.model.Game;
import com.bb.akhambir.library.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {
    public static final String PLAYER_1_ID = "1";
    public static final String PLAYER_2_ID = "2";

    @RequestMapping(value = "/new-game", method = RequestMethod.GET)
    public Game newGame(HttpServletResponse response) {
        response.addCookie(new Cookie("kalahPlayer", PLAYER_1_ID));
        return gameProcessor.newGame();
    }

    @Autowired
    public GameProcessor gameProcessor;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public Game joinGame(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("kalahPlayer", PLAYER_2_ID));
        String gameId = request.getParameter("gameId");
        return gameProcessor.joinGame(gameId);
    }

    @RequestMapping(value = "/doTurn", method = RequestMethod.GET)
    public Game doTurn(@CookieValue("kalahPlayer") String playerId, HttpServletRequest request) {
        String gameId = request.getParameter("gameId");
        String houseId = request.getParameter("houseId");
        if (PLAYER_1_ID.equals(playerId)) {
            return gameProcessor.doTurn(gameId, Integer.valueOf(houseId), Player.PLAYER_ONE);
        } else {
            return gameProcessor.doTurn(gameId, Integer.valueOf(houseId), Player.PLAYER_TWO);
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public Game refresh(@CookieValue("kalahPlayer") String playerId, HttpServletRequest request) {
        String gameId = request.getParameter("gameId");
        if (PLAYER_1_ID.equals(playerId)) {
            return gameProcessor.getGameById(gameId, Player.PLAYER_ONE);
        } else {
            return gameProcessor.getGameById(gameId, Player.PLAYER_TWO);
        }
    }
}
