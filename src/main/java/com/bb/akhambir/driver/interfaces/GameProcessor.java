package com.bb.akhambir.driver.interfaces;

import com.bb.akhambir.library.model.Game;
import com.bb.akhambir.library.model.Player;

public interface GameProcessor {

    Game newGame();

    Game doTurn(String gameId, int houseIndex, Player player);

    Game joinGame(String gameId);

    Game getGameById(String gameId, Player player);
}
