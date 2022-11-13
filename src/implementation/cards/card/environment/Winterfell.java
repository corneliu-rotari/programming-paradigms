package implementation.cards.card.environment;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Objects;

public final class Winterfell extends EnvironmentCard {
    public Winterfell(final CardInput cardInput) {
        super(cardInput);
    }
    public Winterfell(final Winterfell card) {
        super(card);
    }

    @Override
    public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow) {
        cardRow.stream().filter(Objects::nonNull).forEach(card -> card.setFrozen(true));
    }
}
