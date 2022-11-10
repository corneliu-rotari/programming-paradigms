package main.util;

import fileio.CardInput;
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

public final class Determine {
//    TODO transform in singleton
    public Card determineCard(final Card card) {
        return switch (card.getName()) {
            case GameConstants.SENTINEL -> new Sentinel((Sentinel) card);
            case GameConstants.DISCIPLE -> new Disciple((Disciple) card);
            case GameConstants.FIRESTORM -> new Firestorm((Firestorm) card);
            case GameConstants.GOLIATH -> new Goliath((Goliath) card);
            case GameConstants.BERSERKER -> new Berserker((Berserker) card);
            case GameConstants.HEART_HOUND -> new HeartHound((HeartHound) card);
            case GameConstants.MIRAJ -> new Miraj((Miraj) card);
            case GameConstants.THE_CURSED_ONE -> new TheCursedOne((TheCursedOne) card);
            case GameConstants.THE_RIPPER -> new TheRipper((TheRipper) card);
            case GameConstants.WARDEN -> new Warden((Warden) card);
            case GameConstants.WINTERFELL -> new Winterfell((Winterfell) card);
            default -> null;
        };
    }

    public Card determineCard(final CardInput cardInput) {
        return switch (cardInput.getName()) {
            case GameConstants.SENTINEL -> new Sentinel(cardInput);
            case GameConstants.DISCIPLE -> new Disciple(cardInput);
            case GameConstants.FIRESTORM -> new Firestorm(cardInput);
            case GameConstants.GOLIATH -> new Goliath(cardInput);
            case GameConstants.BERSERKER -> new Berserker(cardInput);
            case GameConstants.HEART_HOUND -> new HeartHound(cardInput);
            case GameConstants.MIRAJ -> new Miraj(cardInput);
            case GameConstants.THE_CURSED_ONE -> new TheCursedOne(cardInput);
            case GameConstants.THE_RIPPER -> new TheRipper(cardInput);
            case GameConstants.WARDEN -> new Warden(cardInput);
            case GameConstants.WINTERFELL -> new Winterfell(cardInput);
            default -> null;
        };
    }

}
