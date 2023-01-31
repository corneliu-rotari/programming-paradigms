package implementation.mechanics.player;

import fileio.CardInput;
import fileio.DecksInput;
import lombok.Getter;
import lombok.Setter;
import implementation.cards.Decks;
import implementation.cards.card.Card;
import implementation.cards.card.character.hero.*;
import implementation.cards.card.character.minion.MinionCard;
import implementation.cards.card.environment.EnvironmentCard;
import implementation.mechanics.table.GameTable;
import implementation.utils.Determine;
import implementation.utils.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Player {
    @Getter @Setter private int backRow;
    @Getter @Setter private int frontRow;
    @Getter @Setter private static int nrOfGames = 0;
    @Getter @Setter private int nrOfWins = 0;
    @Setter @Getter private Decks decks;

    @Getter @Setter private int mana = 0;
    @Getter private HeroCard heroCard;

    @Getter private ArrayList<Card> playingDeck;
    @Getter private ArrayList<Card> playingHand;


    public Player(final DecksInput decks, final int frontRow, final int backRow) {
        this.decks = new Decks(decks);
        this.playingDeck = new ArrayList<>();
        this.backRow = backRow;
        this.frontRow = frontRow;
    }

    /**
     * If a player won a game this function is called to recorde the win
     */
    public void setWin() {
        this.nrOfWins++;
    }

    public void setHeroCard(final CardInput cardInput) {
        this.heroCard = (HeroCard) Determine.createCard(cardInput);
    }

    /**
     * Creates a copy of a deck and shuffles it
     * @param deckIdx - deck Index
     * @param random - Random object instance
     */
    public void setPlayingDeck(final int deckIdx, final Random random) {
        this.mana = 0;
        this.playingDeck = new ArrayList<>();
        ArrayList<Card> deckToBePlayed = this.decks.get(deckIdx);
        for (Card card : deckToBePlayed) {
            this.playingDeck.add(Determine.createCard(card));
        }
        Collections.shuffle(this.playingDeck, random);
        this.playingHand = new ArrayList<>();
    }

    /**
     * New round mana addition
     * @param manaToAdd - int
     */
    public void addMana(final int manaToAdd) {
        this.mana += manaToAdd;
    }

    /**
     * Places a new card in the players hand
     */
    public void setNewCardInHand() {
        if (this.playingDeck.size() > 0) {
            this.playingHand.add(this.playingDeck.remove(0));
        }
    }

    /**
     * use Env Card
     * @param handIdx -
     * @param affectedRow -
     * @throws Exception -
     */
    public void useEnvironmentCard(final int handIdx, final int affectedRow) throws Exception {
        if (!Determine.determineEnv(playingHand.get(handIdx))) {
            throw new Exception("Chosen card is not of type environment.");
        }
        EnvironmentCard card = (EnvironmentCard) playingHand.get(handIdx);
        if (this.mana < card.getMana()) {
            throw new Exception("Not enough mana to use environment card.");
        }
        if (affectedRow == this.backRow || affectedRow == this.frontRow) {
            throw new Exception("Chosen row does not belong to the enemy.");
        }
        try {
            card.useAbility(GameTable.getGameTable().getCardTable().get(affectedRow), affectedRow);
        } catch (Exception exception) {
            throw exception;
        }
        this.mana = this.mana - card.getMana();
        this.playingHand.remove(handIdx);
    }

    /**
     * Place card on a row and check if it is a valid place
     * @param card - card to check
     * @throws Exception - no room on table / no more cards
     */
    public void placeCardOnRow(final MinionCard card) throws Exception {
        ArrayList<MinionCard> rowToAdd;
        if (this.playingHand.size() == 0) {
            throw new Exception("There are no more cards in deck");
        }

        if (Determine.determineFrontRowCard(card)) {
            rowToAdd = GameTable.getGameTable().getCardTable().get(this.frontRow);
        } else {
            rowToAdd = GameTable.getGameTable().getCardTable().get(this.backRow);
        }
        for (int i = 0; i < Const.NR_TABLE_COLUMNS; i++) {
            if (rowToAdd.get(i) == null) {
                rowToAdd.add(i, card);
                this.playingHand.remove(card);
                this.mana = this.mana - card.getMana();
                return;
            }
        }
        throw new Exception("Cannot place card on table since row is full.");
    }
}
