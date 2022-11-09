package main.mechanics.table;

import fileio.Input;
import lombok.Getter;
import main.mechanics.player.Player;
import main.util.GameConstants;

public final class GameTable {
    @Getter private static GameTable gameTable = null;

    @Getter private CardTable table;
    private Player playerOne = null;
    private Player playerTwo = null;

    private GameTable(final Input input) {
        this.table = new CardTable(GameConstants.NR_TABLE_ROWS, GameConstants.NR_TABLE_COLUMNS);
        this.playerOne = new Player(input.getPlayerOneDecks());
        this.playerTwo = new Player(input.getPlayerTwoDecks());
    }

    /**
     * Creates a GameTable as a Singleton
     * @param input - input from the I/O for the creation of Players
     * @return - the instances of GameTable
     */
    public static GameTable createGameTable(final Input input) {
        if (gameTable == null) {
            gameTable = new GameTable(input);
        }
        return getGameTable();
    }
}
