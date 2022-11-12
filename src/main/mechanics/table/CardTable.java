package main.mechanics.table;

import fileio.Coordinates;
import lombok.Getter;
import main.cards.card.Card;
import main.cards.card.character.hero.HeroCard;
import main.cards.card.character.minion.MinionCard;
import main.cards.card.character.minion.special.SpecialMinionCard;
import main.mechanics.player.Player;
import main.util.Determine;
import main.util.Const;

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
     * Check if a card is dead or not
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

    public void shiftToLeft(final int rowIdx) {
        ArrayList<MinionCard> row = get(rowIdx);
        for (int i = 0; i < Const.NR_TABLE_COLUMNS; i++) {
            if (row.get(i) == null && (i + 1 != Const.NR_TABLE_COLUMNS) && row.get(i + 1) != null) {
                row.set(i, row.get(i + 1));
                row.set(i + 1, null);
            }
        }
    }

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
    
    public void endTurnDestroyEffects(final Player player) {
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

    public void attackCard(Coordinates attackerCord, Coordinates attackedCord) throws Exception {
        MinionCard attacker = get(attackerCord.getX()).get(attackerCord.getY());
        MinionCard attacked = get(attackedCord.getX()).get(attackedCord.getY());

        try {
            checkCardForAttack(attacker, attacked, attackedCord.getX());
        } catch (Exception exception) {
            throw exception;
        }

        attacker.attackCard(attacked);
    }

    public void useAbility(Coordinates attackerCord, Coordinates attackedCord) throws Exception {
        MinionCard attacker = get(attackerCord.getX()).get(attackerCord.getY());
        MinionCard attacked = get(attackedCord.getX()).get(attackedCord.getY());
        int attackedX = attackedCord.getX();

        if (!attacker.getName().equals(Const.DISCIPLE)) {
            checkCardForAttack(attacker, attacked, attackedX);
        } else {
            if (attacker.isHasAttacked()) {
                throw new Exception("Attacker card has already attacked this turn.");
            } else if (attacker.isFrozen()) {
                throw new Exception("Attacker card is frozen.");
            }else if (attackedX == GameTable.getGameTable().getDefensivePlayer().getFrontRow()
                    || attackedX == GameTable.getGameTable().getDefensivePlayer().getBackRow()) {
                throw new Exception("Attacked card does not belong to the current player.");
            }
        }

        ((SpecialMinionCard) attacker).useAbility(attacked);
        attacker.setHasAttacked(true);
    }

    public void checkCardForAttack(MinionCard attacker, MinionCard attacked,
                                   final int attackedX) throws Exception {
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

    public boolean isRowIsFull(final int row) {
        return get(row).stream().filter(Objects::isNull).count() == Const.NR_TABLE_COLUMNS;
    }

    public boolean hasATank(final int row) {
        return get(row).stream().filter(Objects::nonNull).filter(Determine::isTank).count() > 0;
    }

    public void useHeroAbility(final int row) throws Exception {
        Player player = GameTable.getGameTable().getOffensivePlayer();
        HeroCard hero = player.getHeroCard();

        System.out.println("Hero: " + hero.getName());

        if (player.getMana() < hero.getMana()) {
            throw new Exception("Not enough mana to use hero's ability.");
        } else if (hero.isHasAttacked()) {
            throw new Exception("Hero has already attacked this turn.");
        } else if (Objects.equals(hero.getName(), Const.EMPRESS)
                || Objects.equals(hero.getName(), Const.LORD)) {
            if (row == player.getFrontRow() || row == player.getBackRow()) {
                throw new Exception("Selected row does not belong to the enemy.");
            }
        } else {
            if (row != player.getFrontRow() || row != player.getBackRow()) {
                throw new Exception("Selected row does not belong to the current player.");
            }
        }
        hero.useAbility(get(row));
        player.setMana(player.getMana() - hero.getMana());
        hero.setHasAttacked(true);
    }

    public HeroCard attackHero(Coordinates attackerCord) throws Exception {
        MinionCard attacker = get(attackerCord.getX()).get(attackerCord.getY());
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
