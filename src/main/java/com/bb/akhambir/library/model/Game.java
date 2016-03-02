package com.bb.akhambir.library.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final static int DEFAULT_HOUSE_SIZE = 6;
    private List<Integer> playerHouses = new ArrayList<Integer>(DEFAULT_HOUSE_SIZE);
    private List<Integer> opponentHouses = new ArrayList<Integer>(DEFAULT_HOUSE_SIZE);
    private String gameId;
    private int playerStorage;
    private int opponentStorage;
    private boolean isPlayerTurn;


    public static int getDefaultHouseSize() {
        return DEFAULT_HOUSE_SIZE;
    }

    public List<Integer> getPlayerHouses() {
        return playerHouses;
    }

    public void setPlayerHouses(List<Integer> playerHouses) {
        this.playerHouses = playerHouses;
    }

    public List<Integer> getOpponentHouses() {
        return opponentHouses;
    }

    public void setOpponentHouses(List<Integer> opponentHouses) {
        this.opponentHouses = opponentHouses;
    }

    public int getPlayerStorage() {
        return playerStorage;
    }

    public void setPlayerStorage(int playerStorage) {
        this.playerStorage = playerStorage;
    }

    public int getOpponentStorage() {
        return opponentStorage;
    }

    public void setOpponentStorage(int opponentStorage) {
        this.opponentStorage = opponentStorage;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setIsPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
