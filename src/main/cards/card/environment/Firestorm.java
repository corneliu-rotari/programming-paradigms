package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Objects;

public final class Firestorm extends EnvironmentCard {
    public Firestorm(final CardInput cardInput) {
        super(cardInput);
    }

    public Firestorm(final Firestorm card) {
        super(card);
    }

    /**
     * -1 of every MinionCard health on the row
     * @param cardRow - the row affected
     * @param affectedRow - row Index
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow) {
        cardRow.stream().filter(Objects::nonNull).
                forEach(minionCard -> minionCard.setHealth(minionCard.getHealth() - 1));
    }
}
