package com.bb.akhambir.library.logic.interfaces;

import com.bb.akhambir.library.model.Player;
import java.util.List;

public interface KalahBoard {
    void turnFrom(Player player, int houseNumber);
    List<Integer> getHouses(Player player);
    int getStorage(Player player);
    Player getLastPlayer();
    int getTurnsAmount();
}
