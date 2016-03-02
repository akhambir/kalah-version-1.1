package com.bb.akhambir.library.logic;

import com.bb.akhambir.library.logic.interfaces.KalahBoard;
import com.bb.akhambir.library.model.Player;
import java.util.ArrayList;
import java.util.List;

public class KalahBoardImpl implements KalahBoard {
    private static final int EMPTY_HOUSE = 0;
    private final int PLAYER_ONE_STORAGE_INDEX = 6;
    private final int PLAYER_TWO_STORAGE_INDEX = 13;
    private final int CONTAINER_AMOUNT = 14;
    private Player lastPlayer = Player.PLAYER_TWO; // to make player1 do a first turn
    private int turnsAmount = 0;
    private List<Integer> boardState = new ArrayList<Integer>(CONTAINER_AMOUNT);

    public KalahBoardImpl() {
        final int NUMBER_OF_SEEDS = 6;

        for (int i = 0; i < CONTAINER_AMOUNT; i++) {
            if (i != PLAYER_ONE_STORAGE_INDEX && i != PLAYER_TWO_STORAGE_INDEX) {  //TODO Refactor it (isNotStorage)
                boardState.add(NUMBER_OF_SEEDS);
            } else {
                boardState.add(EMPTY_HOUSE);
            }
        }
    }

    public void turnFrom(Player player, int houseIndex) {
        // ToDo check that current player != lastPlayer
        if (houseIndex != PLAYER_ONE_STORAGE_INDEX && houseIndex != PLAYER_TWO_STORAGE_INDEX) {
            houseIndex = player == Player.PLAYER_TWO ? houseIndex + PLAYER_ONE_STORAGE_INDEX + 1 : houseIndex;
            int numberOfSeeds = boardState.get(houseIndex);
            boardState.set(houseIndex, EMPTY_HOUSE);
            lastPlayer = player;


            int houseToIncrementIndex = houseIndex + 1;
            while (numberOfSeeds > 0) {
                boardState.set(houseToIncrementIndex, boardState.get(houseToIncrementIndex) + 1);
                if (isCapturable(numberOfSeeds, houseToIncrementIndex, player)) {
                    takeOpponentsSeeds(houseToIncrementIndex, player);
                }
                if (isLastSeedToYourStorage(numberOfSeeds, houseToIncrementIndex, player)) {
                    lastPlayer = turnAgain(player);
                }
                houseToIncrementIndex++;
                if (houseToIncrementIndex >= boardState.size()){
                    houseToIncrementIndex = 0;
                }
                numberOfSeeds--;

            }
            turnsAmount++;
        }
    }

    private boolean isLastSeedToYourStorage(int numberOfSeeds, int houseToIncrementIndex, Player player) {
        return isLastSeed(numberOfSeeds) && isYourStorage(houseToIncrementIndex, player);
    }

    private void takeOpponentsSeeds(int houseToIncrementIndex, Player player) {
        final int SEED_FROM_YOUR_HOUSE = 1;
        int opponentsSeeds = boardState.get(opponentsHouseInFront(houseToIncrementIndex)) + SEED_FROM_YOUR_HOUSE;
        boardState.set(opponentsHouseInFront(houseToIncrementIndex), EMPTY_HOUSE);
        boardState.set(houseToIncrementIndex, EMPTY_HOUSE);
        if (player == Player.PLAYER_ONE) {
            boardState.set(PLAYER_ONE_STORAGE_INDEX, boardState.get(PLAYER_ONE_STORAGE_INDEX) + opponentsSeeds);
        } else if (player == Player.PLAYER_TWO) {
            boardState.set(PLAYER_TWO_STORAGE_INDEX, boardState.get(PLAYER_TWO_STORAGE_INDEX) + opponentsSeeds);
        }
    }

    private int opponentsHouseInFront(int houseToIncrementIndex) {
        switch (houseToIncrementIndex) {
            case 0:
                return 12;
            case 1:
                return 11;
            case 2:
                return 10;
            case 3:
                return 9;
            case 4:
                return 8;
            case 5:
                return 7;
            case 7:
                return 5;
            case 8:
                return 4;
            case 9:
                return 3;
            case 10:
                return 2;
            case 11:
                return 1;
            default:
                return 0;
        }
    }

    private boolean isCapturable(int numberOfSeeds, int houseToIncrementIndex, Player player) {
        return isLastSeed(numberOfSeeds) && isNotStorage(houseToIncrementIndex) && isYourSide(player, houseToIncrementIndex);

    }

    private boolean isYourSide(Player player, int houseToIncrementIndex) {
        if (player == Player.PLAYER_ONE && houseToIncrementIndex < PLAYER_ONE_STORAGE_INDEX) {
            return true;
        } else if (player == Player.PLAYER_TWO && houseToIncrementIndex > PLAYER_ONE_STORAGE_INDEX) {
            return true;
        }
        return false;
    }

    private boolean isNotStorage(int houseToIncrementIndex) {
        return houseToIncrementIndex != PLAYER_ONE_STORAGE_INDEX && houseToIncrementIndex != PLAYER_TWO_STORAGE_INDEX;
    }

    private Player turnAgain(Player player) {
        if (player == Player.PLAYER_TWO) {
            return lastPlayer = Player.PLAYER_ONE;
        } else {
            return lastPlayer = Player.PLAYER_TWO;
        }
    }

    private boolean isYourStorage(int houseToIncrementIndex, Player player) {
        if (player == Player.PLAYER_ONE && houseToIncrementIndex == PLAYER_ONE_STORAGE_INDEX) {
            return true;
        } else if (player == Player.PLAYER_TWO && houseToIncrementIndex == PLAYER_TWO_STORAGE_INDEX) {
            return true;
        }
        return false;
    }

    private boolean isLastSeed(int numberOfSeeds) {
        return numberOfSeeds == 1;
    }

    public List<Integer> getHouses(Player player) { // TODO refactor if statements
        if (player == Player.PLAYER_ONE) {
            return boardState.subList(0, PLAYER_ONE_STORAGE_INDEX);
        } else {
            return boardState.subList(PLAYER_ONE_STORAGE_INDEX + 1, PLAYER_TWO_STORAGE_INDEX);
        }
    }

    public int getStorage(Player player) {
        if (player == Player.PLAYER_ONE) {
            return boardState.get(PLAYER_ONE_STORAGE_INDEX);
        } else {
            return boardState.get(PLAYER_TWO_STORAGE_INDEX);
        }
    }

    public Player getLastPlayer() {
        return lastPlayer;
    }

    public int getTurnsAmount() {
        return turnsAmount;
    }
}
