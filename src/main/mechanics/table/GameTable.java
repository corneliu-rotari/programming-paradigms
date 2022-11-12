package main.mechanics.table;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import main.mechanics.player.Player;
import main.util.Const;

import java.util.Random;

public final class GameTable {
    @Getter private static GameTable gameTable = null;
    @Getter private CardTable cardTable;
    @Getter private Player playerOne;
    @Getter private Player playerTwo;

    @Setter private StartGameInput gameCofig;
    @Getter @Setter private int playerTurn;
    @Getter private int manaCapacity;
    private int turnsPlayed = 0;


    private GameTable(final DecksInput playerOne, final DecksInput playerTwo) {
        this.playerOne = new Player(playerOne, Const.PLAYER_ONE_FRONT, Const.PLAYER_ONE_BACK);
        this.playerTwo = new Player(playerTwo, Const.PLAYER_TWO_FRONT, Const.PLAYER_TWO_BACK);
    }

    /**
     * Creates a game table for the players
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public static GameTable getGameTable(final DecksInput playerOne,
                                         final DecksInput playerTwo) {
        if (gameTable == null) {
            gameTable = new GameTable(playerOne, playerTwo);
        }
        return gameTable;
    }

    /**
     * After a game is finished deletes a game table
     */
    public static void deleteGameTable() {
        gameTable = null;
    }

    /**
     * Start a game
     * @param startGameInput - the default config for the game to be played
     */
    public void startGame(final StartGameInput startGameInput) {
        this.cardTable = new CardTable(Const.NR_TABLE_ROWS, Const.NR_TABLE_COLUMNS);
        Player.nrOfGames++;

        System.out.println("New game");
        this.playerOne.setHeroCard(startGameInput.getPlayerOneHero());
        this.playerTwo.setHeroCard(startGameInput.getPlayerTwoHero());

        this.playerTurn = startGameInput.getStartingPlayer();

        this.playerOne.setPlayingDeck(startGameInput.getPlayerOneDeckIdx(),
                new Random(startGameInput.getShuffleSeed()));
        this.playerTwo.setPlayingDeck(startGameInput.getPlayerTwoDeckIdx(),
                new Random(startGameInput.getShuffleSeed()));

        playerTwo.setMana(0);
        playerOne.setMana(0);
        this.manaCapacity = 0;

        this.turnsCounter();
        this.roundStarts();

    }

    /**
     * Return which player is able to take action
     * @return - Player
     */
    public Player getOffensivePlayer() {
        if (this.playerTurn == Const.PLAYER_ONE) {
            return this.playerOne;
        }
        return this.playerTwo;
    }

    public Player getDefensivePlayer() {
        if (this.playerTurn != Const.PLAYER_ONE) {
            return this.playerOne;
        }
        return this.playerTwo;
    }

    /**
     *
     */
    public void endPlayerTurn() {
        cardTable.endTurnDestroyEffects(getOffensivePlayer());
        if (playerTurn == Const.PLAYER_ONE) {
            playerTurn = Const.PLAYER_TWO;
        } else {
            playerTurn = Const.PLAYER_ONE;
        }
        this.turnsCounter();
    }

    private void turnsCounter() {
        if (this.turnsPlayed == 2) {
            roundStarts();
            this.turnsPlayed = 0;
        }
        this.turnsPlayed++;
    }

    private void roundStarts() {
        if (manaCapacity == 0) {
            System.out.println("Round Starts: manaCapacity = " + manaCapacity);
        }
        if (manaCapacity < 10) {
            manaCapacity++;
        }

        this.playerOne.setNewCardInHand();
        this.playerTwo.setNewCardInHand();

        this.playerOne.addMana(manaCapacity);
        this.playerTwo.addMana(manaCapacity);
    }

    public void endGame() {
        getOffensivePlayer().setWin();
        cardTable.endTurnDestroyEffects(getOffensivePlayer());
    }
}
