package main.mechanics.table;

import lombok.Getter;
import main.cards.card.Card;
import main.cards.card.character.minion.MinionCard;
import main.cards.card.environment.EnvironmentCard;
import main.mechanics.player.Player;
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

    public void checkCardsHealth() {
        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            for (int j = 0; j < GameConstants.NR_TABLE_COLUMNS; j++) {
                MinionCard currentCard = cardTable.get(i).get(j);
                if (currentCard != null && currentCard.getHealth() == 0) {
                    this.cardTable.get(i).set(j, null);
                }
            }
        }
    }

    public void placeCard(final int handIdx) throws Exception {

        Player player = GameTable.getGameTable().getPlayer();
        Card cardToPlace = player.getPlayingHand().get(handIdx);
//        Check if ENV_CARD
        if (cardToPlace instanceof EnvironmentCard) {
            throw new Exception("Cannot place environment card on table.");
        }
        if (player.getMana() < cardToPlace.getMana()) {
            throw new Exception("Not enough mana to place card on table.");
        }

//        TODO Add card on board
//         delete card in player hand
//         check if row is full
//        for (int i = 0; i < GameConstants.NR_TABLE_COLUMNS; i++) {
//
//        }
    }
}
