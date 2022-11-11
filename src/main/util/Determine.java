package main.util;

import fileio.CardInput;
import main.cards.card.Card;
import main.cards.card.character.hero.*;
import main.cards.card.character.minion.MinionCard;
import main.cards.card.character.minion.normal.Berserker;
import main.cards.card.character.minion.normal.Goliath;
import main.cards.card.character.minion.normal.Sentinel;
import main.cards.card.character.minion.normal.Warden;
import main.cards.card.character.minion.special.Disciple;
import main.cards.card.character.minion.special.Miraj;
import main.cards.card.character.minion.special.TheCursedOne;
import main.cards.card.character.minion.special.TheRipper;
import main.cards.card.environment.Firestorm;
import main.cards.card.environment.HeartHound;
import main.cards.card.environment.Winterfell;

import java.util.ArrayList;
import java.util.Comparator;

public final class Determine {
    private Determine() {
    }

    /**
     * @param card -
     * @return -
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

    /**
     * @param cardInput -
     * @return -
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
            default -> null;
        };
    }

    /**
     * @param cardInput -
     * @return -
     */
    public static HeroCard createHero(final CardInput cardInput) {
        return switch (cardInput.getName()) {
            case Const.LORD -> new LordRice(cardInput);
            case Const.KING -> new KingMudface(cardInput);
            case Const.EMPRESS -> new EmpressThorina(cardInput);
            case Const.GENERAL -> new GeneralKocioraw(cardInput);
            default -> null;
        };
    }

    /**
     * D
     *
     * @param card -
     * @return -
     */
    public static boolean determineEnv(final Card card) {
        return switch (card.getName()) {
            case Const.FIRESTORM, Const.WINTERFELL, Const.HEART_HOUND -> true;
            default -> false;
        };
    }

    /**
     * D
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

    public static boolean isTank(final Card card) {
        return switch (card.getName()) {
            case Const.GOLIATH, Const.WARDEN -> true;
            default -> false;
        };
    }

}
