package main.cards.card.character.hero;

import fileio.CardInput;
import main.cards.card.character.CharacterCard;
import main.util.Const;

public abstract class HeroCard extends CharacterCard {
    public HeroCard(final CardInput cardInput) {
        super(cardInput, Const.HERO_HEALTH);
    }

    /**
     * Check if the hero is dead
     * @return - boolean
     */
    public boolean isHeroDead() {
        return this.health == 0;
    }

}
