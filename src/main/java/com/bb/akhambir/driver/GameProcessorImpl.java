package com.bb.akhambir.driver;

import com.bb.akhambir.driver.interfaces.GameProcessor;
import com.bb.akhambir.library.logic.KalahBoardImpl;
import com.bb.akhambir.library.logic.interfaces.KalahBoard;
import com.bb.akhambir.library.model.Game;
import com.bb.akhambir.library.model.Player;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GameProcessorImpl implements GameProcessor {
    private static Map<String, KalahBoard> boards = new HashMap<String, KalahBoard>();

    public Game newGame() {
        String gameId = new Date().getTime() + "";
        boards.put(gameId, new KalahBoardImpl());

        return convertKalahBoardToGame(gameId, Player.PLAYER_ONE);
    }

    public Game doTurn(String gameId, int houseIndex, Player player){
        KalahBoard board = boards.get(gameId);
        if (player != board.getLastPlayer()) {
            board.turnFrom(player, houseIndex);
        }

        return convertKalahBoardToGame(gameId, player);
    }

    public Game joinGame(String gameId) {
        return convertKalahBoardToGame(gameId, Player.PLAYER_TWO);
    }

    public Game getGameById(String gameId, Player player) {
        return convertKalahBoardToGame(gameId, player);
    }

    private Game convertKalahBoardToGame(String gameId, Player player) {
        Game game = new Game();
        KalahBoard board = boards.get(gameId);
        Player current;
        Player opponent;
        if (player == Player.PLAYER_ONE) {
            current = Player.PLAYER_ONE;
            opponent = Player.PLAYER_TWO;
        } else {
            current = Player.PLAYER_TWO;
            opponent = Player.PLAYER_ONE;
        }
        game.setPlayerHouses(board.getHouses(current));
        game.setPlayerStorage(board.getStorage(current));
        game.setOpponentHouses(board.getHouses(opponent));
        game.setOpponentStorage(board.getStorage(opponent));
        game.setGameId(gameId);
        game.setIsPlayerTurn(board.getLastPlayer() != player);
        return game;
    }

}
