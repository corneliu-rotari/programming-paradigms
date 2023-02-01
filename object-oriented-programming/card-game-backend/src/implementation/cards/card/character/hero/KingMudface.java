package implementation.cards.card.character.hero;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Objects;


public final class KingMudface extends HeroCard {
    public KingMudface(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Adds to his MinionCards 1 point of health
     * @param affectedRow - the valid row to alter
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> affectedRow) {
        affectedRow.stream().filter(Objects::nonNull).
                forEach(minionCard -> minionCard.setHealth(minionCard.getHealth() + 1));
    }
}
