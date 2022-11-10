package main.mechanics.table;

import lombok.Getter;
import main.cards.card.character.minion.MinionCard;
import main.util.GameConstants;

import java.util.ArrayList;

public final class CardTable {
    @Getter private final ArrayList<ArrayList<MinionCard>> cardTable;
    public CardTable(final int rows, final int columns) {
        this.cardTable = new ArrayList<>(rows);

        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            this.cardTable.add(new ArrayList<>(columns));
            for (int j = 0; j < GameConstants.NR_TABLE_COLUMNS; j++) {
                this.cardTable.get(i).add(null);
            }
        }
    }

    public void printCardTable() {
        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            for (int j = 0; j < GameConstants.NR_TABLE_COLUMNS; j++) {
                if (this.cardTable.get(i).get(j) == null) {
                    System.out.print("[]");
                } else {
                    System.out.print("[" + this.cardTable.get(i).get(j).getName() + "]");
                }
            }
            System.out.println();
        }
    }

    /**
     * Get the deck on position index
     * @param index - position of deck
     * @return - deck of cards
     */
    public ArrayList<MinionCard> get(final int index) {
        return this.cardTable.get(index);
    }
}
