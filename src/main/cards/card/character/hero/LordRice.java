package main.cards.card.character.hero;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public final class LordRice extends HeroCard {
    public LordRice(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Freezes the opponent MinionCard with the most attackDamage
     * @param affectedRow - the valid row to alter
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> affectedRow) {
        affectedRow.stream().filter(Objects::nonNull).
                max(Comparator.comparing(MinionCard::getAttackDamage)).
                ifPresent(affectedCard -> affectedCard.setFrozen(true));
    }
}
