package main.cards.card.character.minion.special;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

public abstract class SpecialMinionCard extends MinionCard{
    public SpecialMinionCard(CardInput cardInput) {
        super(cardInput);
    }

    public SpecialMinionCard(MinionCard card) {
        super(card);
    }

//    abstract public void useAbility();
}
