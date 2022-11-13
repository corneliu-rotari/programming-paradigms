package implementation.cards.card.character.hero;

import fileio.CardInput;
import implementation.cards.card.character.CharacterCard;
import implementation.cards.card.character.minion.MinionCard;
import implementation.util.Const;

import java.util.ArrayList;

public abstract class HeroCard extends CharacterCard {
    public HeroCard(final CardInput cardInput) {
        super(cardInput, Const.HERO_HEALTH);
    }

    /**
     * Check if the hero is dead
     * @return - boolean
     */
    public boolean isHeroDead() {
        return this.health <= 0;
    }

    /**
     * Hero card has a special ability
     * @param affectedRow - the valid row to alter
     */
    public abstract void useAbility(ArrayList<MinionCard> affectedRow);
}
