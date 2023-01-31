package implementation.cards.card.character.hero;

import fileio.CardInput;
import implementation.cards.card.character.minion.MinionCard;
import implementation.utils.Const;

import java.util.ArrayList;

public final class EmpressThorina extends HeroCard {
    public EmpressThorina(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Removes form the opponent MinionCard with biggest attackDamage
     * @param affectedRow - the valid row to alter
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> affectedRow) {
        MinionCard max = affectedRow.get(0);
        for (int i = 1; i < Const.NR_TABLE_COLUMNS; i++) {
            if (affectedRow.get(i) != null) {
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
