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

    @Override
    public void useAbility(MinionCard attacked) {
        System.out.println("From " + attacked.getAttackDamage());
        attacked.setAttackDamage(attacked.getAttackDamage() - 2);
        System.out.println("To " + attacked.getAttackDamage());

    }
}
