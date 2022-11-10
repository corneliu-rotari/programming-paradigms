package main.cards.card.character.minion;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.character.CharacterCard;

public abstract class MinionCard extends CharacterCard {
    @Getter @Setter protected int attackDamage;
    public MinionCard(final CardInput cardInput) {
        super(cardInput, cardInput.getHealth());
        this.attackDamage = cardInput.getAttackDamage();
    }
    public MinionCard(final MinionCard card) {
        super(card, card.getHealth());
        this.attackDamage = card.getAttackDamage();
    }
}
