package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;
import main.mechanics.table.GameTable;

import java.util.ArrayList;

public final class Firestorm extends EnvironmentCard {
    public Firestorm(final CardInput cardInput) {
        super(cardInput);
    }

    public Firestorm(final Firestorm card) {
        super(card);
    }

    @Override
    public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow) {
        cardRow.forEach(minionCard -> minionCard.setHealth(minionCard.getHealth() - 1));
        GameTable.getGameTable().getCardTable().checkCardsHealth();
    }
}
