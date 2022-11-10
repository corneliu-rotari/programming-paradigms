package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import lombok.Getter;
import lombok.Setter;
import main.cards.card.Card;
import main.mechanics.player.Player;
import main.mechanics.table.GameTable;
import main.util.GameConstants;

import java.util.ArrayList;
import java.util.Objects;

public final class CommandController {
    private ObjectNode objectNode;
    private ArrayNode output;
    private ObjectMapper mapper = new ObjectMapper();
    private GameTable gameTable;
    private DebugAndStatsCommands debugAndStatsCommands;

    @Setter @Getter private ArrayList<ActionsInput> actions;

    public CommandController(ArrayNode output, GameTable gameTable) {
        this.output = output;
        this.gameTable = gameTable;
        this.debugAndStatsCommands = new DebugAndStatsCommands(gameTable);
    }

    public void determineAction(ActionsInput actionsInput) {
        this.objectNode = new ObjectMapper().createObjectNode();
        this.objectNode.put("command", actionsInput.getCommand());
        switch (actionsInput.getCommand()) {
            case GameConstants.GET_PLAYER_DECK ->
                    outputJSON(actionsInput.getPlayerIdx(),
                            this.gameTable.getPlayerOne().getPlayingDeck(),
                            this.gameTable.getPlayerTwo().getPlayingDeck());
            case GameConstants.GET_CARDS_IN_HAND ->
                    outputJSON(actionsInput.getPlayerIdx(),
                            this.gameTable.getPlayerOne().getPlayingCards(),
                            this.gameTable.getPlayerTwo().getPlayingCards());
            case GameConstants.GET_PLAYER_HERO ->
                    outputJSON(actionsInput.getPlayerIdx(),
                            this.gameTable.getPlayerOne().getHeroCard(),
                            this.gameTable.getPlayerTwo().getHeroCard());
            case GameConstants.GET_PLAYER_ONE_WINS ->
                outputJSON(this.gameTable.getPlayerOne().getNrOfWins());
            case GameConstants.GET_PLAYER_TWO_WINS ->
                outputJSON(this.gameTable.getPlayerTwo().getNrOfWins());
            case GameConstants.GET_TOTAL_GAMES_PLAYED ->
                outputJSON(Player.nrOfGames);
            case GameConstants.GET_PLAYER_TURN ->
                outputJSON(this.gameTable.getPlayerTurn());
            case GameConstants.GET_CARDS_ON_TABLE ->
                outputJSON(this.gameTable.getTable().getCards());
//  TODO          case GameConstants.GET_CARD_AT_POS->
//  TODO          case GameConstants.GET_ENV_CARD_IN_HAND ->
//  TODO          case GameConstants.GET_FROZEN_CARDS_ON_TABLE ->
            case GameConstants.GET_PLAYER_MANA ->
                outputJSON(actionsInput.getPlayerIdx(),
                        this.gameTable.getPlayerOne().getMana(),
                        this.gameTable.getPlayerTwo().getMana());
            default -> {}
        }
        output.add(this.objectNode);
    }

    private void outputJSON(final int number) {
        this.objectNode.put("output", number);
    }

    private void outputJSON(final int playerIndex,
                            final int numberOne, final int numberTwo) {
        if (playerIndex == GameConstants.PLAYER_ONE) {
            this.objectNode.put("output", numberOne);
        } else {
            this.objectNode.put("output", numberTwo);
        }
    }

    private void outputJSON(final int playerIndex,
                            final ArrayList<Card> listOne, final ArrayList<Card> listTwo) {
        this.objectNode.put("playerIdx", playerIndex);
        if (playerIndex == GameConstants.PLAYER_ONE) {
            this.objectNode.set("output", mapper.valueToTree(listOne));
        } else {
            this.objectNode.set("output", mapper.valueToTree(listTwo));
        }
    }

    private void outputJSON(final int playerIndex, final Card card1, final Card card2) {
        this.objectNode.put("playerIdx", playerIndex);
        if (playerIndex == GameConstants.PLAYER_ONE) {
            this.objectNode.set("output", mapper.valueToTree(card1));
        } else {
            this.objectNode.set("output", mapper.valueToTree(card2));
        }
    }

    private void outputJSON(ArrayList<ArrayList<Card>> cards) {
        ArrayList<ArrayList<Card>> outputArray = new ArrayList<>();
        for (int i = 0; i < GameConstants.NR_TABLE_ROWS; i++) {
            ArrayList<Card> newRow = new ArrayList<>();
            cards.get(i).stream().filter(Objects::nonNull).forEach(newRow::add);
            outputArray.add(newRow);
        }
        this.objectNode.set("output", mapper.valueToTree(outputArray));
    }

}
