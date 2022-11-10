package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import lombok.Getter;
import lombok.Setter;
import main.mechanics.table.GameTable;
import main.util.GameConstants;

import java.util.ArrayList;

public final class CommandController {
    private ArrayNode output;
    private ObjectNode objectNode;
    private ObjectMapper mapper = new ObjectMapper();
    private GameTable gameTable;

    @Setter @Getter private ArrayList<ActionsInput> actions;

    public CommandController(ArrayNode output) {
        this.output = output;
    }

    public void determineAction(ActionsInput actionsInput, GameTable gameTable) {
        this.gameTable = gameTable;
        this.objectNode = new ObjectMapper().createObjectNode();
        this.objectNode.put("command", actionsInput.getCommand());
        switch (actionsInput.getCommand()) {
            case GameConstants.GET_PLAYER_DECK -> getPlayerDeck(actionsInput.getPlayerIdx());
            case GameConstants.GET_PLAYER_TURN -> getPlayerTurn();
            case GameConstants.GET_PLAYER_HERO -> getPlayerHero(actionsInput.getPlayerIdx());
            default -> {
            }
        }
        output.add(this.objectNode);
    }

    private void getPlayerDeck(final int playerIndex) {
        this.objectNode.put("playerIdx", playerIndex);
        ArrayNode deck;
        if (playerIndex == 1) {
            deck = mapper.valueToTree(this.gameTable.getPlayerOne().getPlayingHand());
        } else {
            deck = mapper.valueToTree(this.gameTable.getPlayerTwo().getPlayingHand());
        }
        this.objectNode.putArray("output").addAll(deck);
    }

    private void getPlayerTurn() {
        this.objectNode.put("output", this.gameTable.getPlayerTurn());
    }

    private void getPlayerHero(final int playerIndex) {
        this.objectNode.put("playerIdx", playerIndex);
        ObjectNode deck;
        if (playerIndex == 1) {
            deck = mapper.valueToTree(this.gameTable.getPlayerOne().getHeroCard());
        } else {
            deck = mapper.valueToTree(this.gameTable.getPlayerTwo().getHeroCard());
        }
        this.objectNode.set("output",deck);
    }
}
