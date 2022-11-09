package main.mechanics.player;

import fileio.DecksInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.Decks;
import main.util.MatrixOfCards;

public final class Player {
    @Setter @Getter private MatrixOfCards deck;
    @Getter @Setter private int nrOfGames = 0;
    @Getter @Setter private int nrOfWins = 0;
    @Getter @Setter private int manaCapacity = 0;


    // TODO 09-Nov-22 Add Player character

    public Player(final DecksInput decks) {
        this.deck = new Decks(decks);
    }

    /**
     * After a game is played the counter of total games goes up
     */
    public void setGame() {
        this.nrOfGames++;
    }

    /**
     * If a player won a game this function is called
     */
    public void setWin() {
        this.nrOfWins++;
    }
}
