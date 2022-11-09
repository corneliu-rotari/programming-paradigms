package main.cards;

import fileio.CardInput;
import fileio.DecksInput;
import main.cards.card.Card;
import main.cards.card.character.minion.normal.Berserker;
import main.cards.card.character.minion.normal.Goliath;
import main.cards.card.character.minion.normal.Sentinel;
import main.cards.card.character.minion.normal.Warden;
import main.cards.card.character.minion.special.Disciple;
import main.cards.card.character.minion.special.Miraj;
import main.cards.card.character.minion.special.TheCursedOne;
import main.cards.card.character.minion.special.TheRipper;
import main.cards.card.environment.Firestorm;
import main.cards.card.environment.HeartHound;
import main.cards.card.environment.Winterfell;
import main.util.GameConstants;
import main.util.MatrixOfCards;

public class Decks extends MatrixOfCards {

    public Decks(final DecksInput deck) {
        super(deck.getNrDecks(), deck.getNrCardsInDeck());
        for (int i = 0; i < deck.getNrDecks(); i++) {
            for (int j = 0; j < deck.getNrCardsInDeck(); j++) {
                this.getMatrixOfCards().get(i).add(determineCard(deck.getDecks().get(i).get(j)));
            }
        }
    }

    private Card determineCard(final CardInput cardInput) {
        return switch (cardInput.getName()) {
            case GameConstants.SENTINEL -> new Sentinel(cardInput);
            case GameConstants.DISCIPLE -> new Disciple(cardInput);
            case GameConstants.FIRESTORM -> new Firestorm(cardInput);
            case GameConstants.GOLIATH -> new Goliath(cardInput);
            case GameConstants.BERSERKER -> new Berserker(cardInput);
            case GameConstants.HEART_HOUND -> new HeartHound(cardInput);
            case GameConstants.MIRAJ -> new Miraj(cardInput);
            case GameConstants.THE_CURSED_ONE -> new TheCursedOne(cardInput);
            case GameConstants.THE_REAPER -> new TheRipper(cardInput);
            case GameConstants.WARDEN -> new Warden(cardInput);
            case GameConstants.WINTERFALL -> new Winterfell(cardInput);
            default -> null;
        };
    }
}
