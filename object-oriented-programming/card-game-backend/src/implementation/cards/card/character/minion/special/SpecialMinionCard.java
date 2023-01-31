package implementation.cards.card.character.minion.special;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;

public abstract class SpecialMinionCard extends MinionCard {
    public SpecialMinionCard(final CardInput cardInput) {
        super(cardInput);
    }

    public SpecialMinionCard(final MinionCard card) {
        super(card);
    }

    /**
     * Special Minions need a special ability
     * @param attacked - MinionCard attacked
     */
    public abstract void useAbility(MinionCard attacked);
}
