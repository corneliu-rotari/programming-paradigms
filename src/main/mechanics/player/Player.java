package main.mechanics.player;

import fileio.CardInput;
import fileio.DecksInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.Decks;
import main.cards.card.Card;
import main.cards.card.character.hero.*;
import main.cards.card.character.minion.MinionCard;
import main.cards.card.environment.EnvironmentCard;
import main.mechanics.table.GameTable;
import main.util.Determine;
import main.util.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Player {
    @Getter @Setter private int backRow;
    @Getter @Setter private int frontRow;
    public static int nrOfGames = 0;
    @Getter @Setter private int nrOfWins = 0;
    @Setter @Getter private Decks decks;

    @Getter private int mana = 0;
    @Getter private HeroCard heroCard;

    @Getter private ArrayList<Card> playingDeck;
    @Getter private ArrayList<Card> playingHand;


    public Player(final DecksInput decks, final int frontRow, final int backRow) {
        this.decks = new Decks(decks);
        this.playingHand = new ArrayList<>();
        this.playingDeck = new ArrayList<>();
        this.backRow = backRow;
        this.frontRow = frontRow;
    }

    /**
     * If a player won a game this function is called
     */
    public void setWin() {
        this.nrOfWins++;
    }

    public void setHeroCard(CardInput cardInput) {
        this.heroCard = Determine.createHero(cardInput);
    }

    public void setPlayingDeck(ArrayList<Card> playingHand, Random random) {
        for (Card card : playingHand) {
            this.playingDeck.add(Determine.createCard(card));
        }
        Collections.shuffle(this.playingDeck, random);
    }
    public void setMana(final int manaCapacity) {
        this.mana += manaCapacity;
    }

    public void setNewCardInHand() {
        this.playingHand.add(this.playingDeck.remove(0));
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
        if (false/* TODO Need to check if row is full*/) {
            throw new Exception("Cannot steal enemy card since the player's row is full.");
        }
        card.useAbility(GameTable.getGameTable().getCardTable().get(affectedRow), affectedRow);
        this.playingHand.remove(handIdx);
    }

    /**
     * Place card
     * @param card -
     * @throws Exception -
     */
    public void placeCardOnRow(final MinionCard card) throws Exception {
        ArrayList<MinionCard> rowToAdd;
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
