package main.cards.card.character.minion.special;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

public final class TheCursedOne extends SpecialMinionCard {
    public TheCursedOne(final CardInput cardInput) {
        super(cardInput);
    }

    public TheCursedOne(final TheCursedOne card) {
        super(card);
    }

    @Override
    public void useAbility(MinionCard attacked) {
        int temp = attacked.getHealth();
        attacked.setHealth(attacked.getAttackDamage());
        attacked.setAttackDamage(temp);
    }

}
