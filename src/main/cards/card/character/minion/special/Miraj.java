package main.cards.card.character.minion.special;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

public final class Miraj extends SpecialMinionCard {
    public Miraj(final CardInput cardInput) {
        super(cardInput);
    }
    public Miraj(final Miraj card) {
        super(card);
    }

    @Override
    public void useAbility(MinionCard attacked) {
        int temp = this.health;
        this.health = attacked.getHealth();
        attacked.setHealth(temp);
        System.out.println("Miraj made " + attacked.getName() + " health " + attacked.getHealth());
    }
}
