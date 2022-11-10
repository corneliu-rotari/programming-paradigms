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

    @Getter private ArrayList<Card> playingHand;
    @Getter private ArrayList<Card> playingCards;


    // TODO 09-Nov-22 Add Player character

    public Player(final DecksInput decks) {
        this.decks = new Decks(decks);
    }

    /**
     * If a player won a game this function is called
     */
    public void setWin() {
        this.nrOfWins++;
    }

    public void setHeroCard(CardInput cardInput) {
        this.heroCard = switch (cardInput.getName()) {
            case GameConstants.LORD -> new LordRice(cardInput);
            case GameConstants.KING -> new KingMudface(cardInput);
            case GameConstants.EMPRESS -> new EmpressThorina(cardInput);
            case GameConstants.GENERAL -> new GeneralKocioraw(cardInput);
            default -> null;
        };
    }

    public void setPlayingHand(ArrayList<Card> playingHand, Random random) {
        this.playingHand = new ArrayList<>();
        for (Card card : playingHand) {
            this.playingHand.add(new Determine().determineCard(card));
        }
        Collections.shuffle(this.playingHand, random);

        this.playingCards = new ArrayList<>();
        this.playingCards.add(this.playingHand.remove(0));
    }
}
