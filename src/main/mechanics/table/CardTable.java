package main.mechanics.table;

import main.cards.Decks;
import main.cards.card.Card;
import main.util.GameConstants;

import java.util.ArrayList;

public final class CardTable extends Decks {
    public CardTable(final int rows, final int columns) {
        super(rows, columns);
        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            for (int j = 0; j < GameConstants.NR_TABLE_COLUMNS; j++) {
                this.decks.get(i).add(null);
            }
        }
    }
    public ArrayList<ArrayList<Card>> getCards(){
        return this.getDecks();
    }

    public void printCardTable() {
        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            for (int j = 0; j < GameConstants.NR_TABLE_COLUMNS; j++) {
                if (this.decks.get(i).get(j) == null) {
                    System.out.print("[]");
                } else {
                    System.out.print("["+this.decks.get(i).get(j).getName()+"]");
                }
            }
            System.out.println();
        }
    }
}
