package implementation.mechanics.table;

import fileio.Coordinates;
import lombok.Getter;
import implementation.cards.card.Card;
import implementation.cards.card.character.hero.HeroCard;
import implementation.cards.card.character.minion.MinionCard;
import implementation.cards.card.character.minion.special.SpecialMinionCard;
import implementation.mechanics.player.Player;
import implementation.utils.Determine;
import implementation.utils.Const;

import java.util.ArrayList;
import java.util.Objects;

public final class CardTable {
    @Getter private final ArrayList<ArrayList<MinionCard>> cardTable;
    public CardTable(final int rows, final int columns) {
        this.cardTable = new ArrayList<>(rows);

        for (int i = 0; i < Const.NR_TABLE_ROWS; i++) {
            this.cardTable.add(new ArrayList<>(columns));
            for (int j = 0; j < Const.NR_TABLE_COLUMNS; j++) {
                get(i).add(null);
            }
        }
    }

    /**
     * Print Card Table with Minion Names
     */
    public void printCardTable() {
        for (int i = 0; i < Const.NR_TABLE_ROWS; i++) {
            for (int j = 0; j < Const.NR_TABLE_COLUMNS; j++) {
                if (get(i).get(j) == null) {
                    System.out.print("[]");
                } else {
                    System.out.print("[" + get(i).get(j).getName() + "]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Get the deck on position index
     * @param index - position of deck
     * @return - deck of cards
     */
    public ArrayList<MinionCard> get(final int index) {
        return this.cardTable.get(index);
    }

    /**
     * Check if a card is dead or not and calls the shift method
     */
    public void checkCardsHealth() {
        for (int i = 0; i < Const.NR_TABLE_ROWS; i++) {
            for (int j = 0; j < Const.NR_TABLE_COLUMNS; j++) {
                MinionCard currentCard = get(i).get(j);
                if (currentCard != null && currentCard.isDead()) {
                    get(i).set(j, null);
                    shiftToLeft(i);
                }
            }
        }
    }

    /**
     * If a card is dead we shift all the cards to the left side of the board
     * @param rowIdx - which row to shift
     */
    public void shiftToLeft(final int rowIdx) {
        ArrayList<MinionCard> row = get(rowIdx);
        for (int i = 0; i < Const.NR_TABLE_COLUMNS; i++) {
            if (row.get(i) == null && (i + 1 != Const.NR_TABLE_COLUMNS) && row.get(i + 1) != null) {
                row.set(i, row.get(i + 1));
                row.set(i + 1, null);
            }
        }
    }

    /**
     *  Checks if a specific card can be place on the board and places it
     * @param handIdx - the index of the card form the players hand
     * @throws Exception - if the card cannot be placed on the board
     */
    public void placeCard(final int handIdx) throws Exception {
        Player player = GameTable.getGameTable().getOffensivePlayer();
        Card cardToPlace = player.getPlayingHand().get(handIdx);


        if (Determine.determineEnv(cardToPlace)) {
            throw new Exception("Cannot place environment card on table.");
        }

        if (player.getMana() < cardToPlace.getMana()) {
            throw new Exception("Not enough mana to place card on table.");
        }
        try {
            player.placeCardOnRow(((MinionCard) cardToPlace));
        } catch (Exception exception) {
            throw exception;
        }
    }

    /**
     * Removes effects after the player's turn ends
     * @param player - from whom to remove the effects
     */
    public void destroyEffects(final Player player) {
        player.getHeroCard().setHasAttacked(false);
        get(player.getBackRow()).stream().filter(Objects::nonNull).forEach(minionCard -> {
                    minionCard.setFrozen(false);
                    minionCard.setHasAttacked(false);
                });
        get(player.getFrontRow()).stream().filter(Objects::nonNull).forEach(minionCard -> {
                    minionCard.setFrozen(false);
                    minionCard.setHasAttacked(false);
                });
    }

    /**
     * Lets a Minion Card attack another card
     * @param attackerCord - the coordinates of the attacking card
     * @param attackedCord - the coordinates of the defending  card
     * @throws Exception - if the card is unable to attack
     */
    public void attackCard(final Coordinates attackerCord, final Coordinates attackedCord)
            throws Exception {
        MinionCard attacker = get(attackerCord.getX()).get(attackerCord.getY());
        MinionCard attacked = get(attackedCord.getX()).get(attackedCord.getY());
        Player playerOff = GameTable.getGameTable().getOffensivePlayer();
        /*
        Error Check
         */
        try {
            checkCardForAttack(attacker, attacked, attackedCord.getX());
        } catch (Exception exception) {
            throw exception;
        }

        attacker.attackCard(attacked);
    }

    /**
     * Lets a Special Minion to use his ability
     * @param attackerCord - the coordinates of the attacking card
     * @param attackedCord - the coordinates of the defending  card
     * @throws Exception - if the card is unable the ability
     */
    public void useAbility(final Coordinates attackerCord, final Coordinates attackedCord)
            throws Exception {
        MinionCard attacker = get(attackerCord.getX()).get(attackerCord.getY());
        MinionCard attacked = get(attackedCord.getX()).get(attackedCord.getY());
        Player playerDef = GameTable.getGameTable().getDefensivePlayer();
        int attackedX = attackedCord.getX();
        /*
        Error Check
         */
        if (!attacker.getName().equals(Const.DISCIPLE)) {
            checkCardForAttack(attacker, attacked, attackedX);
        } else {
            if (attacker.isHasAttacked()) {
                throw new Exception("Attacker card has already attacked this turn.");
            } else if (attacker.isFrozen()) {
                throw new Exception("Attacker card is frozen.");
            } else if (attackedX == playerDef.getFrontRow()
                    || attackedX == playerDef.getBackRow()) {
                throw new Exception("Attacked card does not belong to the current player.");
            }
        }

        ((SpecialMinionCard) attacker).useAbility(attacked);
        attacker.setHasAttacked(true);
    }

    /**
     * Check if the card is able to attack another card
     * @param attacker - the attacking card
     * @param attacked - the one who receives the damage
     * @param attackedX - position of the attacked card
     * @throws Exception - if the card cannot attack
     */
    private void checkCardForAttack(final MinionCard attacker, final MinionCard attacked,
                                   final int attackedX) throws Exception {
        /*
        Error Check
         */
        if (attacker.isHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        } else if (attacker.isFrozen()) {
            throw new Exception("Attacker card is frozen.");
        } else if (attackedX == GameTable.getGameTable().getOffensivePlayer().getFrontRow()
                || attackedX == GameTable.getGameTable().getOffensivePlayer().getBackRow()) {
            throw new Exception("Attacked card does not belong to the enemy.");
        } else if (!Determine.isTank(attacked)
                && hasATank(GameTable.getGameTable().getDefensivePlayer().getFrontRow())) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }
    }

    /**
     * Check if there is a place to place a card
     * @param row
     * @return
     */
    public boolean isRowIsFull(final int row) {
        return get(row).stream().filter(Objects::isNull).count() == Const.NR_TABLE_COLUMNS;
    }

    /**
     * Check if hte front row has a tank
     * @param row - the index of the front row of the player
     * @return - boolean
     */
    public boolean hasATank(final int row) {
        return get(row).stream().filter(Objects::nonNull).filter(Determine::isTank).count() > 0;
    }

    /**
     * Makes the card use its ability
     * @param row - affected row
     * @throws Exception - if the player doesn't have mana
     */
    public void useHeroAbility(final int row) throws Exception {
        Player offensivePlayer = GameTable.getGameTable().getOffensivePlayer();
        Player defensivePlayer = GameTable.getGameTable().getDefensivePlayer();
        HeroCard hero = offensivePlayer.getHeroCard();
        /*
        Error Check
         */
        if (offensivePlayer.getMana() < hero.getMana()) {
            throw new Exception("Not enough mana to use hero's ability.");
        } else if (hero.isHasAttacked()) {
            throw new Exception("Hero has already attacked this turn.");
        } else if (Objects.equals(hero.getName(), Const.EMPRESS)
                || Objects.equals(hero.getName(), Const.LORD)) {
            if (row == offensivePlayer.getFrontRow() || row == offensivePlayer.getBackRow()) {
                throw new Exception("Selected row does not belong to the enemy.");
            }
        } else if (Objects.equals(hero.getName(), Const.KING)
                || Objects.equals(hero.getName(), Const.GENERAL)) {
            if (row == defensivePlayer.getFrontRow() || row == defensivePlayer.getBackRow()) {
                throw new Exception("Selected row does not belong to the current player.");
            }
        }
        hero.useAbility(get(row));
        offensivePlayer.setMana(offensivePlayer.getMana() - hero.getMana());
        hero.setHasAttacked(true);
    }

    /**
     * Attacks the player's hero card
     * @param attackerCord - the position on board of the attacker
     * @return - hero to check if its dead
     * @throws Exception - invalid input
     */
    public HeroCard attackHero(final Coordinates attackerCord) throws Exception {
        MinionCard attacker = get(attackerCord.getX()).get(attackerCord.getY());
        /*
        Error Check
         */
        if (attacker.isHasAttacked()) {
            throw new Exception("Attacker card has already attacked this turn.");
        } else if (attacker.isFrozen()) {
            throw new Exception("Attacker card is frozen.");
        } else if (hasATank(GameTable.getGameTable().getDefensivePlayer().getFrontRow())) {
            throw new Exception("Attacked card is not of type 'Tank'.");
        }

        HeroCard hero = GameTable.getGameTable().getDefensivePlayer().getHeroCard();
        attacker.setHasAttacked(true);
        hero.setHealth(hero.getHealth() - attacker.getAttackDamage());
        return hero;
    }
}
