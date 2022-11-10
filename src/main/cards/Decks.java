package main.cards;

import fileio.DecksInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;
import main.util.Determine;
import main.util.GameConstants;

import java.util.ArrayList;

public class Decks {
    @Getter @Setter protected ArrayList<ArrayList<Card>> decks;

    protected Decks(final int rows, final int columns) {
        this.decks = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            this.decks.add(new ArrayList<>(columns));
        }
    }

    public Decks(final DecksInput deck) {
        this(deck.getNrDecks(), deck.getNrCardsInDeck());
        for (int i = 0; i < deck.getNrDecks(); i++) {
            for (int j = 0; j < deck.getNrCardsInDeck(); j++) {
                this.decks.get(i).add(
                        Determine.createCard(deck.getDecks().get(i).get(j)));
            }
        }
    }

    /**
     * Get the deck on position index
     * @param index - position of deck
     * @return - deck of cards
     */
    public ArrayList<Card> get(final int index) {
        return this.decks.get(index);
    }

}
