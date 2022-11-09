package main.util;

import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;

import java.util.ArrayList;

public abstract class MatrixOfCards {
    @Setter @Getter private ArrayList<ArrayList<Card>> matrixOfCards;

    protected MatrixOfCards(final int rows, final int columns) {
        this.matrixOfCards = new ArrayList<ArrayList<Card>>(rows);
        for (int i = 0; i < rows; i++) {
            this.matrixOfCards.add(i, new ArrayList<Card>(columns));
        }
    }

    // TODO 09-Nov-22 Posibil de lucru aici dupa

//    public void determineCard(Card card) {
//        switch (card.getName()) {
//            case GameConstants.SENTINEL:
//                card.action();
//                break;
//            case GameConstants.BERSERKER:
//                card.action()
//            default:
//                break;
//        }
//    }
}
