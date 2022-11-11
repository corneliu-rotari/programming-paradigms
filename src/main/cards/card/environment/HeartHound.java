package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;
import main.mechanics.table.GameTable;
import main.util.Const;

import java.util.ArrayList;

public final class HeartHound extends EnvironmentCard {
    public HeartHound(final CardInput cardInput) {
        super(cardInput);
    }

    public HeartHound(final HeartHound card) {
        super(card);
    }

    @Override
    public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow) {
        int mirrorRowIdx = getMirrorRow(affectedRow);
        ArrayList<MinionCard> mirrorRow = GameTable.getGameTable().getCardTable().get(mirrorRowIdx);

        for (int i = 0; i < Const.NR_TABLE_COLUMNS; i++) {
            if (mirrorRow.get(i) == null) {
//                TODO max of a row (here we steel)
//                cardRow.stream().map(CharacterCard::getHealth).max(Integer::compareTo);
                return;
            }
        }
    }


}
