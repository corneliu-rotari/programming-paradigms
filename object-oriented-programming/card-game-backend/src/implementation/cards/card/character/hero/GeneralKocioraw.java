package implementation.cards.card.character.hero;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Objects;

public final class GeneralKocioraw extends HeroCard {
    public GeneralKocioraw(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Adds to his MinionCards 1 point of attackDamage
     * @param affectedRow - the valid row to alter
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> affectedRow) {
        affectedRow.stream().filter(Objects::nonNull).
                forEach(minionCard -> minionCard.setAttackDamage(minionCard.getAttackDamage() + 1));
    }
}
