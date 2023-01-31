package implementation.cards.card.character.minion.special;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

public final class TheCursedOne extends SpecialMinionCard {
    public TheCursedOne(final CardInput cardInput) {
        super(cardInput);
    }

    public TheCursedOne(final TheCursedOne card) {
        super(card);
    }

    /**
     * Shape shift: swaps the attacked card health and attackDamage
     * @param attacked - MinionCard attacked
     */
    @Override
    public void useAbility(final MinionCard attacked) {
        int temp = attacked.getHealth();
        attacked.setHealth(attacked.getAttackDamage());
        attacked.setAttackDamage(temp);
    }

}
