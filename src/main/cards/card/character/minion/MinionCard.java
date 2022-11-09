package main.cards.card.character.minion;

import fileio.CardInput;
import main.cards.card.character.CharacterCard;

public abstract class MinionCard extends CharacterCard {
    public MinionCard(final CardInput cardInput) {
        super(cardInput, cardInput.getHealth());
    }
}
