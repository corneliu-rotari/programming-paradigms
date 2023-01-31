package implementation.mechanics.table;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import implementation.mechanics.player.Player;
import implementation.utils.Const;

import java.util.Random;

public final class GameTable {
    @Getter private static GameTable gameTable = null;
    @Getter private CardTable cardTable;
    @Getter private Player playerOne;
    @Getter private Player playerTwo;

    @Getter @Setter private int playerTurn;
    @Getter private int manaCapacity;
    private int turnsPlayed = 0;
    @Getter private boolean gameFinished = false;


    private GameTable(final DecksInput playerOne, final DecksInput playerTwo) {
        this.playerOne = new Player(playerOne, Const.PLAYER_ONE_FRONT, Const.PLAYER_ONE_BACK);
        this.playerTwo = new Player(playerTwo, Const.PLAYER_TWO_FRONT, Const.PLAYER_TWO_BACK);
    }

    /**
     * Creates a game table for the players
     * @param playerOne - first player deck
     * @param playerTwo - second player deck
     * @return - instance of GameTable
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
     * @param startGameInput - configuration for the game to be played
     */
    public void startGame(final StartGameInput startGameInput) {
        this.gameFinished = false;
        this.cardTable = new CardTable(Const.NR_TABLE_ROWS, Const.NR_TABLE_COLUMNS);
        Player.setNrOfGames(Player.getNrOfGames() + 1);

        this.playerOne.setHeroCard(startGameInput.getPlayerOneHero());
        this.playerTwo.setHeroCard(startGameInput.getPlayerTwoHero());

        this.playerTurn = startGameInput.getStartingPlayer();

        this.playerOne.setPlayingDeck(startGameInput.getPlayerOneDeckIdx(),
                new Random(startGameInput.getShuffleSeed()));
        this.playerTwo.setPlayingDeck(startGameInput.getPlayerTwoDeckIdx(),
                new Random(startGameInput.getShuffleSeed()));

        this.manaCapacity = 0;
        this.turnsPlayed = 0;
        this.playerOne.setMana(0);
        this.playerTwo.setMana(0);

        this.turnsCounter();
        this.roundStarts();

    }

    /**
     * Return player is able to attack
     * @return - Player
     */
    public Player getOffensivePlayer() {
        if (this.playerTurn == Const.PLAYER_ONE) {
            return this.playerOne;
        }
        return this.playerTwo;
    }
    /**
     * Return player receives the attack
     * @return - Player
     */
    public Player getDefensivePlayer() {
        if (this.playerTurn != Const.PLAYER_ONE) {
            return this.playerOne;
        }
        return this.playerTwo;
    }

    /**
     * Destroys the effects on card and switches player turn
     */
    public void endPlayerTurn() {
        cardTable.destroyEffects(getOffensivePlayer());
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

    /**
     * Starts new round and adds new cards in playing hand
     */
    private void roundStarts() {
        if (manaCapacity < Const.MAX_MANA) {
            manaCapacity++;
        }

        this.playerOne.setNewCardInHand();
        this.playerTwo.setNewCardInHand();

        this.playerOne.addMana(manaCapacity);
        this.playerTwo.addMana(manaCapacity);
    }

    /**
     * Ends a game and interrupts game-input
     */
    public void endGame() {
        getOffensivePlayer().setWin();
        cardTable.destroyEffects(getOffensivePlayer());
        this.gameFinished = true;
    }
}
