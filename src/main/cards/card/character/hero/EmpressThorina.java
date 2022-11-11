package main.cards.card.character.hero;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public final class EmpressThorina extends HeroCard {
    public EmpressThorina(final CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void useAbility(ArrayList<MinionCard> affectedRow) {
//        TODO Posibil nu merge
        affectedRow.stream().filter(Objects::nonNull).
                max(Comparator.comparing(MinionCard::getAttackDamage)).
                ifPresent(affectedCard -> affectedCard = null);
    }
}
