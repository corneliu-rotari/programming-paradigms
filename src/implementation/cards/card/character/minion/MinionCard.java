package implementation.cards.card.character.minion;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;
import implementation.cards.card.character.CharacterCard;

public abstract class MinionCard extends CharacterCard {
    @Getter protected int attackDamage;
    @Getter @Setter protected boolean isFrozen = false;

    public MinionCard(final CardInput cardInput) {
        super(cardInput, cardInput.getHealth());
        this.attackDamage = cardInput.getAttackDamage();
    }
    public MinionCard(final MinionCard card) {
        super(card, card.getHealth());
        this.attackDamage = card.getAttackDamage();
        this.isFrozen = false;
    }

    /**
     * Removes health point from the opponent
     * @param card - opponent
     */
    public void attackCard(final MinionCard card) {
        card.setHealth(card.getHealth() - this.attackDamage);
        this.hasAttacked = true;
    }

    /**
     * Sets a new value to the attack damage
     * @param attackDamage - new attack damage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = Math.max(attackDamage, 0);
    }
}
