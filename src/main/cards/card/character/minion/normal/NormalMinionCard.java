package main.cards.card.character.minion.normal;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

public abstract class NormalMinionCard extends MinionCard {
    public NormalMinionCard(final CardInput cardInput) {
        super(cardInput);
    }

    public NormalMinionCard(final MinionCard card) {
        super(card);
    }
}
