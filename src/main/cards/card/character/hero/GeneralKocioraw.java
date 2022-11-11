package main.cards.card.character.hero;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Objects;

public final class GeneralKocioraw extends HeroCard {
    public GeneralKocioraw(final CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void useAbility(ArrayList<MinionCard> affectedRow) {
        affectedRow.stream().filter(Objects::nonNull).
                forEach(minionCard -> minionCard.setAttackDamage(minionCard.getAttackDamage() + 1));
    }
}
