package main.cards.card.character.hero;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;
import main.util.Const;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public final class EmpressThorina extends HeroCard {
    public EmpressThorina(final CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void useAbility(ArrayList<MinionCard> affectedRow) {
        MinionCard max = affectedRow.get(0);
        for (int i = 1; i < Const.NR_TABLE_COLUMNS; i++) {
            if ( affectedRow.get(i) != null) {
                if (max != null && max.getAttackDamage() <= affectedRow.get(i).getAttackDamage()) {
                    max = affectedRow.get(i);
                } else if (max == null) {
                    max = affectedRow.get(i);
                }
            }
        }
        if (max != null) {
            max.setHealth(0);
        }

    }
}
