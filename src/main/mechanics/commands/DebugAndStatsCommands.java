package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;
import main.cards.card.character.CharacterCard;
import main.cards.card.character.minion.MinionCard;
import main.cards.card.environment.EnvironmentCard;
import main.mechanics.player.Player;
import main.mechanics.table.GameTable;
import main.util.GameConstants;

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

    public DebugAndStatsCommands(final ArrayNode output, final GameTable gameTable) {
        this.output = output;
        this.gameTable = gameTable;
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
            case GameConstants.GET_PLAYER_DECK -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getPlayingDeck());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getPlayingDeck());
                }
            }

            case GameConstants.GET_CARDS_IN_HAND -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getPlayingCards());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getPlayingCards());
                }
            }

            case GameConstants.GET_PLAYER_HERO -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getHeroCard());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getHeroCard());
                }
            }
            case GameConstants.GET_PLAYER_ONE_WINS ->
                    outputJSON(this.gameTable.getPlayerOne().getNrOfWins());

            case GameConstants.GET_PLAYER_TWO_WINS ->
                    outputJSON(this.gameTable.getPlayerTwo().getNrOfWins());

            case GameConstants.GET_TOTAL_GAMES_PLAYED -> outputJSON(Player.nrOfGames);
            case GameConstants.GET_PLAYER_TURN -> outputJSON(this.gameTable.getPlayerTurn());
            case GameConstants.GET_CARDS_ON_TABLE ->
                    outputTableJSON(this.gameTable.getTable().getCardTable());
            case GameConstants.GET_ENV_CARD_IN_HAND -> {
                ArrayList<EnvironmentCard> outputArray = new ArrayList<>();
                ArrayList<Card> inputArray;
                if (isPlayerOne()) {
                    inputArray = this.gameTable.getPlayerOne().getPlayingCards();
                } else {
                    inputArray = this.gameTable.getPlayerTwo().getPlayingCards();
                }
                inputArray.stream().filter(card -> card instanceof EnvironmentCard).
                        forEach(card -> outputArray.add((EnvironmentCard) card));
                this.objectNode.set("output", mapper.valueToTree(inputArray));
            }

            case GameConstants.GET_FROZEN_CARDS_ON_TABLE -> {
//                TODO Posibil greseli cu ordinea cartilor in output
                ArrayList<ObjectNode> outputArray = new ArrayList<>();
                this.gameTable.getTable().getCardTable()
                        .forEach(minionCardsList -> minionCardsList.stream().
                                filter(Objects::nonNull).filter(CharacterCard::isFrozen).
                                forEach(minionCard -> outputArray.add(removeFrozen(minionCard))));
                this.objectNode.set("output", this.mapper.valueToTree(outputArray));
            }
            case GameConstants.GET_PLAYER_MANA -> {
                if (isPlayerOne()) {
                    outputJSON(this.gameTable.getPlayerOne().getMana());
                } else {
                    outputJSON(this.gameTable.getPlayerTwo().getMana());
                }
            }
            case GameConstants.GET_CARD_AT_POS -> {
                Card card = this.gameTable.getTable().get(actionsInput.getX())
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
        return this.playerIdx == GameConstants.PLAYER_ONE;
    }

    private void outputJSON(final int number) {
        this.objectNode.put("output", number);
    }

    private void outputJSON(final Card card) {
        this.objectNode.set("output", removeFrozen(card));
    }

    private void outputJSON(final ArrayList<Card> list) {
        ArrayList<ObjectNode> outputNode = new ArrayList<>();
        for (Card card: list) {
            outputNode.add(removeFrozen(card));
        }
        this.objectNode.set("output", this.mapper.valueToTree(outputNode));
    }

    private void outputTableJSON(final ArrayList<ArrayList<MinionCard>> cards) {
        ArrayList<ArrayList<ObjectNode>> outputArray = new ArrayList<>();
        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            ArrayList<ObjectNode> newRow = new ArrayList<>();
            cards.get(i).stream().filter(Objects::nonNull).
                    forEach(minionCard -> newRow.add(removeFrozen(minionCard)));
            outputArray.add(newRow);
        }
        this.objectNode.set("output", this.mapper.valueToTree(outputArray));
    }

    private ObjectNode removeFrozen(final Card card) {
        ObjectNode node = this.mapper.valueToTree(card);
        node.remove("frozen");
        return node;
    }
}
