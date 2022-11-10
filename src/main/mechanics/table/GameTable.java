package main.mechanics.table;

import fileio.*;
import lombok.Getter;
import lombok.Setter;
import main.mechanics.player.Player;
import main.util.GameConstants;

import java.util.Random;

public final class GameTable {
    @Getter private CardTable table;
    @Getter private Player playerOne;
    @Getter private Player playerTwo;

    @Getter @Setter private int playerTurn;

    public GameTable() {
        this.table = new CardTable(GameConstants.NR_TABLE_ROWS, GameConstants.NR_TABLE_COLUMNS);
    }

    public GameTable(final DecksInput playerOne, final DecksInput playerTwo) {
        this();
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);

    }

    /**
     * Start a game
     * @param startGameInput - the default config for the game to be played
     */
    public void startGame(final StartGameInput startGameInput) {
        Player.nrOfGames++;

        this.playerOne.setHeroCard(startGameInput.getPlayerOneHero());
        this.playerTwo.setHeroCard(startGameInput.getPlayerTwoHero());

        this.playerTurn = startGameInput.getStartingPlayer();

        this.playerOne.setPlayingHand(this.playerOne.getDecks().get(startGameInput
                .getPlayerOneDeckIdx()), new Random(startGameInput.getShuffleSeed()));
        this.playerTwo.setPlayingHand(this.playerTwo.getDecks().get(startGameInput
                .getPlayerTwoDeckIdx()), new Random(startGameInput.getShuffleSeed()));

    }

}
