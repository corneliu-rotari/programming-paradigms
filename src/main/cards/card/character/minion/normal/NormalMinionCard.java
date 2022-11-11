package main.cards.card.character.minion.normal;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

public abstract class NormalMinionCard extends MinionCard {
    public NormalMinionCard(CardInput cardInput) {
        super(cardInput);
    }

    public NormalMinionCard(MinionCard card) {
        super(card);
    }
}
