package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.character.minion.MinionCard;
import main.mechanics.table.GameTable;
import main.util.Const;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public final class HeartHound extends EnvironmentCard {
    public HeartHound(final CardInput cardInput) {
        super(cardInput);
    }

    public HeartHound(final HeartHound card) {
        super(card);
    }

    @Override
    public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow)
                                                                                throws Exception {
        int mirrorRowIdx = getMirrorRow(affectedRow);
        ArrayList<MinionCard> mirrorRow = GameTable.getGameTable().getCardTable().get(mirrorRowIdx);

        if (GameTable.getGameTable().getCardTable().isRowIsFull(affectedRow)) {
            throw new Exception("Cannot steal enemy card since the player's row is full.");
        }
        for (int i = 0; i < Const.NR_TABLE_COLUMNS; i++) {
            if (mirrorRow.get(i) == null) {
                mirrorRow.set(i, cardRow.stream().filter(Objects::nonNull)
                        .max(Comparator.comparing(MinionCard::getHealth)).orElse(null));
                return;
            }
        }
    }


}
