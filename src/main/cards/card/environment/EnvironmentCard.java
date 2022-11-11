package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.Card;
import main.cards.card.character.minion.MinionCard;
import main.util.Const;

import java.util.ArrayList;

public abstract class EnvironmentCard extends Card {

    public EnvironmentCard(final CardInput cardInput) {
        super(cardInput);
    }

    public EnvironmentCard(final EnvironmentCard card) {
        super(card);
    }


    abstract public void useAbility(ArrayList<MinionCard> cardRow, int affectedRow) throws Exception;

    public int getMirrorRow(final int affectedRow) {
        int mirrorRow;
        if (affectedRow == Const.PLAYER_TWO_FRONT) {
            mirrorRow = Const.PLAYER_ONE_FRONT;
        } else if (affectedRow == Const.PLAYER_ONE_FRONT) {
            mirrorRow = Const.PLAYER_TWO_FRONT;
        } else if (affectedRow == Const.PLAYER_TWO_BACK) {
            mirrorRow = Const.PLAYER_ONE_BACK;
        } else {
            mirrorRow = Const.PLAYER_TWO_BACK;
        }
        return mirrorRow;
    }
}
