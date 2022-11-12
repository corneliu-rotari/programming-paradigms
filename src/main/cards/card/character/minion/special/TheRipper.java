package main.cards.card.character.minion.special;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

public final class TheRipper extends SpecialMinionCard {
    public TheRipper(final CardInput cardInput) {
        super(cardInput);
    }
    public TheRipper(final TheRipper card) {
        super(card);
    }

    /**
     * Weak Knees: -2 attackDamage to a minion card
     * @param attacked - MinionCard attacked
     */
    @Override
    public void useAbility(final MinionCard attacked) {
        attacked.setAttackDamage(attacked.getAttackDamage() - 2);
    }
}
