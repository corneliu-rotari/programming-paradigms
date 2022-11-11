package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;
import main.cards.card.character.minion.MinionCard;
import main.cards.card.environment.EnvironmentCard;
import main.mechanics.player.Player;
import main.mechanics.table.GameTable;
import main.util.Determine;
import main.util.Const;

import java.util.ArrayList;
import java.util.Objects;

public final class DebugAndStatsCommands implements CommandUser {
    private int playerIdx;
    private ObjectNode objectNode;
    private ArrayNode output;
    private ObjectMapper mapper = new ObjectMapper();
    private GameTable gameTable;

    @Setter
    @Getter
    private ArrayList<ActionsInput> actions;

    public DebugAndStatsCommands(final ArrayNode output) {
        this.output = output;
        this.gameTable = GameTable.getGameTable();
    }

    /**
     * C
     * @param actionsInput - command information
     */
    public void determineCommand(final ActionsInput actionsInput) {
        this.playerIdx = actionsInput.getPlayerIdx();
        this.objectNode = new ObjectMapper().createObjectNode();

        this.objectNode.put("command", actionsInput.getCommand());
        if (this.playerIdx != 0) {
            this.objectNode.put("playerIdx", this.playerIdx);
        }

        switch (actionsInput.getCommand()) {
            case Const.GET_PLAYER_DECK -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getPlayingDeck());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getPlayingDeck());
                }
            }

            case Const.GET_CARDS_IN_HAND -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getPlayingHand());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getPlayingHand());
                }
            }

            case Const.GET_PLAYER_HERO -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getHeroCard());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getHeroCard());
                }
            }
            case Const.GET_PLAYER_ONE_WINS ->
                    outputJSON(this.gameTable.getPlayerOne().getNrOfWins());

            case Const.GET_PLAYER_TWO_WINS ->
                    outputJSON(this.gameTable.getPlayerTwo().getNrOfWins());

            case Const.GET_TOTAL_GAMES_PLAYED -> outputJSON(Player.nrOfGames);
            case Const.GET_PLAYER_TURN -> outputJSON(this.gameTable.getPlayerTurn());
            case Const.GET_CARDS_ON_TABLE ->
                    outputTableJSON(this.gameTable.getCardTable().getCardTable());
            case Const.GET_ENV_CARD_IN_HAND -> {
                ArrayList<EnvironmentCard> outputArray = new ArrayList<>();
                ArrayList<Card> inputArray;
                if (isPlayerOne()) {
                    inputArray = this.gameTable.getPlayerOne().getPlayingHand();
                } else {
                    inputArray = this.gameTable.getPlayerTwo().getPlayingHand();
                }
                //                TODO Posibil greseli cu ordinea cartilor in output

                inputArray.stream().filter(Determine::determineEnv).toList().
                        forEach(card -> outputArray.add((EnvironmentCard) card));
                this.objectNode.set("output", mapper.valueToTree(outputArray));
            }

            case Const.GET_FROZEN_CARDS_ON_TABLE -> {
//                TODO Posibil greseli cu ordinea cartilor in output
                ArrayList<ObjectNode> outputArray = new ArrayList<>();
                this.gameTable.getCardTable().getCardTable()
                        .forEach(minionCardsList -> minionCardsList.stream().
                                filter(Objects::nonNull).filter(MinionCard::isFrozen).
                                forEach(minionCard -> outputArray.add(removeFiledNames(minionCard))));
                this.objectNode.set("output", this.mapper.valueToTree(outputArray));
            }
            case Const.GET_PLAYER_MANA -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getMana());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getMana());
                }
            }
            case Const.GET_CARD_AT_POS -> {
                Card card = this.gameTable.getCardTable().get(actionsInput.getX())
                        .get(actionsInput.getY());
                if (card == null) {
                    this.objectNode.put("output", "No card at that position.");
                } else {
                    outputJSON(card);
                }
            }
            default -> {
            }
        }
        output.add(this.objectNode);
    }

    private boolean isPlayerOne() {
        return this.playerIdx == Const.PLAYER_ONE;
    }

    private void outputJSON(final int number) {
        this.objectNode.put("output", number);
    }

    private void outputJSON(final Card card) {
        this.objectNode.set("output", removeFiledNames(card));
    }

    private void outputJSON(final ArrayList<Card> list) {
        ArrayList<ObjectNode> outputNode = new ArrayList<>();
        for (Card card: list) {
            outputNode.add(removeFiledNames(card));
        }
        this.objectNode.set("output", this.mapper.valueToTree(outputNode));
    }

    private void outputTableJSON(final ArrayList<ArrayList<MinionCard>> cards) {
        ArrayList<ArrayList<ObjectNode>> outputArray = new ArrayList<>();
        for (int i = 0; i < Const.NR_TABLE_ROWS; i++) {
            ArrayList<ObjectNode> newRow = new ArrayList<>();
            cards.get(i).stream().filter(Objects::nonNull).
                    forEach(minionCard -> newRow.add(removeFiledNames(minionCard)));
            outputArray.add(newRow);
        }
        this.objectNode.set("output", this.mapper.valueToTree(outputArray));
    }

    private ObjectNode removeFiledNames(final Card card) {
        ObjectNode node = this.mapper.valueToTree(card);
        node.remove("frozen");
        node.remove("hasAttacked");
        node.remove("heroDead");
        return node;
    }
}
