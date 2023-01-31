package implementation.cards.card.character.minion.special;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

public final class Miraj extends SpecialMinionCard {
    public Miraj(final CardInput cardInput) {
        super(cardInput);
    }
    public Miraj(final Miraj card) {
        super(card);
    }

    /**
     * Skyjack: Swaps his health with the attacked card
     * @param attacked - MinionCard attacked
     */
    @Override
    public void useAbility(final MinionCard attacked) {
        int temp = this.health;
        this.health = attacked.getHealth();
        attacked.setHealth(temp);
    }
}
