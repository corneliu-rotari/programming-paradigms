package implementation.cards.card.environment;

import fileio.CardInput;
import implementation.cards.card.Card;
import implementation.cards.card.character.minion.MinionCard;
import implementation.util.Const;

import java.util.ArrayList;

public abstract class EnvironmentCard extends Card {

    public EnvironmentCard(final CardInput cardInput) {
        super(cardInput);
    }

    public EnvironmentCard(final EnvironmentCard card) {
        super(card);
    }

    /**
     * Environment Card have natural abilities
     * @param cardRow - the row affected
     * @param affectedRow - row Index
     * @throws Exception - error to print to the output
     */
    public abstract void useAbility(ArrayList<MinionCard> cardRow, int affectedRow)
            throws Exception;

    /**
     * Mirror row to player's row
     * @param affectedRow row Index
     * @return mirror row Index
     */
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
