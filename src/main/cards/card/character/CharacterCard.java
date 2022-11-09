package main.cards.card.character;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;

public abstract class CharacterCard extends Card {
    @Getter @Setter protected int health;
    public CharacterCard(final CardInput cardInput, final int health) {
        super(cardInput);
        this.health = health;
    }
}
