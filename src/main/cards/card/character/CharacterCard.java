package main.cards.card.character;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;

public abstract class CharacterCard extends Card {
    @Getter @Setter protected int health;
    @Getter @Setter protected boolean hasAttacked = false;

    public CharacterCard(final CardInput cardInput, final int health) {
        super(cardInput);
        this.health = health;
    }

    public CharacterCard(final Card card, final int health) {
        super(card);
        this.health = health;
    }

    /**
     * Check if health < 0
     * @return - boolean
     */
    public boolean isDead() {
        return this.health <= 0;
    }

}
