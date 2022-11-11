package main.cards.card.environment;

import fileio.CardInput;
import main.cards.card.Card;
import main.cards.card.character.minion.MinionCard;
import main.util.GameConstants;

import java.util.ArrayList;

public abstract class EnvironmentCard extends Card{

    public EnvironmentCard(final CardInput cardInput) {
        super(cardInput);
    }

    public EnvironmentCard(final EnvironmentCard card) {
        super(card);
    }

    abstract public void useAbility(final ArrayList<MinionCard> cardRow, final int affectedRow);

    public int getMirrorRow(final int affectedRow) {
        int mirrorRow;
        if (affectedRow == GameConstants.PLAYER_TWO_FRONT) {
            mirrorRow = GameConstants.PLAYER_ONE_FRONT;
        } else if (affectedRow == GameConstants.PLAYER_ONE_FRONT) {
            mirrorRow = GameConstants.PLAYER_TWO_FRONT;
        } else if (affectedRow == GameConstants.PLAYER_TWO_BACK) {
            mirrorRow = GameConstants.PLAYER_ONE_BACK;
        } else {
            mirrorRow = GameConstants.PLAYER_TWO_BACK;
        }
        return mirrorRow;
    }
}
