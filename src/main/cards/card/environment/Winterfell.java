package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

import java.util.ArrayList;

public final class Winterfell extends EnvironmentCard {
    public Winterfell(final CardInput cardInput) {
        super(cardInput);
    }
    public Winterfell(final Winterfell card) {
        super(card);
    }

    @Override
    public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow){
        cardRow.forEach(card -> card.setFrozen(true));
    }
}
