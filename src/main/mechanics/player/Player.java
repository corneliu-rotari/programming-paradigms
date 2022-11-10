package main.mechanics.player;

import fileio.CardInput;
import fileio.DecksInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.Decks;
import main.cards.card.Card;
import main.cards.card.character.hero.*;
import main.util.Determine;
import main.util.GameConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Player {
    public static int nrOfGames = 0;
    @Getter @Setter private int nrOfWins = 0;
    @Setter @Getter private Decks decks;

    @Getter @Setter private int mana = 0;
    @Getter private HeroCard heroCard;

    @Getter private ArrayList<Card> playingDeck;
    @Getter private ArrayList<Card> playingCards;


    public Player(final DecksInput decks) {
        this.decks = new Decks(decks);
        this.playingCards = new ArrayList<>();
        this.playingDeck = new ArrayList<>();
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

        this.playingCards.add(this.playingDeck.remove(0));
    }
}
