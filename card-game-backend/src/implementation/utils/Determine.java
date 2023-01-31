package implementation.utils;

import fileio.CardInput;
import implementation.cards.card.Card;
import implementation.cards.card.character.hero.*;
import implementation.cards.card.character.minion.normal.Berserker;
import implementation.cards.card.character.minion.normal.Goliath;
import implementation.cards.card.character.minion.normal.Sentinel;
import implementation.cards.card.character.minion.normal.Warden;
import implementation.cards.card.character.minion.special.Disciple;
import implementation.cards.card.character.minion.special.Miraj;
import implementation.cards.card.character.minion.special.TheCursedOne;
import implementation.cards.card.character.minion.special.TheRipper;
import implementation.cards.card.environment.Firestorm;
import implementation.cards.card.environment.HeartHound;
import implementation.cards.card.environment.Winterfell;

public final class Determine {
    private Determine() {
    }

    /** Determines which card it is
     * @param card - instance of Card
     * @return - instance of Card
     */
    public static Card createCard(final Card card) {
        return switch (card.getName()) {
            case Const.SENTINEL -> new Sentinel((Sentinel) card);
            case Const.DISCIPLE -> new Disciple((Disciple) card);
            case Const.FIRESTORM -> new Firestorm((Firestorm) card);
            case Const.GOLIATH -> new Goliath((Goliath) card);
            case Const.BERSERKER -> new Berserker((Berserker) card);
            case Const.HEART_HOUND -> new HeartHound((HeartHound) card);
            case Const.MIRAJ -> new Miraj((Miraj) card);
            case Const.THE_CURSED_ONE -> new TheCursedOne((TheCursedOne) card);
            case Const.THE_RIPPER -> new TheRipper((TheRipper) card);
            case Const.WARDEN -> new Warden((Warden) card);
            case Const.WINTERFELL -> new Winterfell((Winterfell) card);
            default -> null;
        };
    }

    /** Determines which card it is
     * @param cardInput - instanceof CardInput
     * @return - instance of Card
     */
    public static Card createCard(final CardInput cardInput) {
        return switch (cardInput.getName()) {
            case Const.SENTINEL -> new Sentinel(cardInput);
            case Const.DISCIPLE -> new Disciple(cardInput);
            case Const.FIRESTORM -> new Firestorm(cardInput);
            case Const.GOLIATH -> new Goliath(cardInput);
            case Const.BERSERKER -> new Berserker(cardInput);
            case Const.HEART_HOUND -> new HeartHound(cardInput);
            case Const.MIRAJ -> new Miraj(cardInput);
            case Const.THE_CURSED_ONE -> new TheCursedOne(cardInput);
            case Const.THE_RIPPER -> new TheRipper(cardInput);
            case Const.WARDEN -> new Warden(cardInput);
            case Const.WINTERFELL -> new Winterfell(cardInput);
            case Const.LORD -> new LordRice(cardInput);
            case Const.KING -> new KingMudface(cardInput);
            case Const.EMPRESS -> new EmpressThorina(cardInput);
            case Const.GENERAL -> new GeneralKocioraw(cardInput);
            default -> null;
        };
    }

    /**
     * Determines if a card is environment
     * @param card -
     * @return - boolean
     */
    public static boolean determineEnv(final Card card) {
        return switch (card.getName()) {
            case Const.FIRESTORM, Const.WINTERFELL, Const.HEART_HOUND -> true;
            default -> false;
        };
    }

    /**
     * Determines if a card is supposed to be in the first row
     *
     * @param card -
     * @return -
     */
    public static boolean determineFrontRowCard(final Card card) {
        return isTank(card) || switch (card.getName()) {
            case Const.THE_RIPPER, Const.MIRAJ -> true;
            default -> false;
        };
    }

    /**
     * Determines if a card is a tank
     * @param card - the card checked
     * @return - boolean
     */
    public static boolean isTank(final Card card) {
        return switch (card.getName()) {
            case Const.GOLIATH, Const.WARDEN -> true;
            default -> false;
        };
    }

}
