package main.cards.card;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public abstract class Card {
    @Getter @Setter protected int mana;
    @Getter @Setter protected String name;
    @Getter @Setter protected String description;
    @Getter @Setter protected ArrayList<String> colors;

    protected Card(final CardInput cardInput) {
        this.mana = cardInput.getMana();
        this.name = cardInput.getName();
        this.description = cardInput.getDescription();
        this.colors = cardInput.getColors();
    }

    protected Card(final Card card) {
        this.mana = card.getMana();
        this.name = card.getName();
        this.description = card.getDescription();
        this.colors = card.getColors();
    }

    protected Card() {}
}
