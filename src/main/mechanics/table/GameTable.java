package main.mechanics.table;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import main.mechanics.player.Player;
import main.util.GameConstants;

import java.util.Random;

public final class GameTable {
    @Getter private static GameTable gameTable = null;
    @Getter private CardTable table;
    @Getter private Player playerOne;
    @Getter private Player playerTwo;
    private Random random = new Random();

    @Getter @Setter private int playerTurn;

    private GameTable() {
        this.table = new CardTable(GameConstants.NR_TABLE_ROWS, GameConstants.NR_TABLE_COLUMNS);
    }

    private GameTable(final DecksInput playerOne, final DecksInput playerTwo) {
        this();
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);

    }
    private void setSeed(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Creates a table for the game
     * @param playerOne- player 1 Decks of cards
     * @param playerTwo - player 2 Decks of cards
     * @return - the instances of GameTable
     */
    public static  GameTable createGameTable(final DecksInput playerOne,
                                            final DecksInput playerTwo) {
        if (gameTable == null) {
            gameTable = new GameTable(playerOne, playerTwo);
        }
        return getGameTable();
    }

    /**
     * Start a game
     * @param startGameInput - the default config for the game to be played
     */
    public void startGame(final StartGameInput startGameInput) {
        this.setSeed(startGameInput.getShuffleSeed());

        this.playerOne.setHeroCard(startGameInput.getPlayerOneHero());
        this.playerTwo.setHeroCard(startGameInput.getPlayerTwoHero());

        this.playerTurn = startGameInput.getStartingPlayer();
        this.playerOne.setPlayingHand(this.playerOne.getDecks().get(startGameInput
                .getPlayerOneDeckIdx()), this.random);
        this.playerTwo.setPlayingHand(this.playerTwo.getDecks().get(startGameInput
                .getPlayerTwoDeckIdx()), this.random);

    }

}
