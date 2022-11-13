package implementation.cards.card.character.minion.special;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

public final class Disciple extends SpecialMinionCard {
    public Disciple(final CardInput cardInput) {
        super(cardInput);
    }

    public Disciple(final Disciple card) {
        super(card);
    }

    /**
     * God's Plan: +1 health to players Minions
     * @param attacked - MinionCard attacked
     */
    @Override
    public void useAbility(final MinionCard attacked) {
        attacked.setHealth(attacked.getHealth() + 2);
    }

}
