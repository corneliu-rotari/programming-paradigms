package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import main.mechanics.table.GameTable;
import main.util.Const;

public final class GameControlCommands implements CommandUser {
    private ObjectNode objectNode;
    private ArrayNode output;
    private GameTable gameTable;
    public GameControlCommands(final ArrayNode output) {
        this.gameTable = GameTable.getGameTable();
        this.output = output;
    }

    /**
     * C
     * @param actionsInput - command information
     */
    public void determineCommand(final ActionsInput actionsInput) {
        this.objectNode = new ObjectMapper().createObjectNode();
        this.objectNode.put("command", actionsInput.getCommand());
        try {
            switch (actionsInput.getCommand()) {
                case Const.END_PLAYER_TURN ->
                        this.gameTable.endPlayerTurn();
                case Const.PLACE_CARD -> {
                    this.gameTable.getCardTable().printCardTable();
                    this.gameTable.getCardTable().placeCard(actionsInput.getHandIdx());
                }
                case Const.CARD_USES_ABILITY -> {
                }
                case Const.CARD_USES_ATTACK -> {
                }
                case Const.USE_ATTACK_ON_HERO -> {
                }
                case Const.USE_ENV_CARD -> {
                    this.objectNode.put("handIdx", actionsInput.getHandIdx());
                    this.objectNode.put("affectedRow", actionsInput.getAffectedRow());
                    this.gameTable.getPlayer().useEnvironmentCard(actionsInput.getHandIdx(),
                            actionsInput.getAffectedRow());
                }
                case Const.USE_HERO_ABILITY -> {
                }
                default -> {
                }
            }
        } catch (Exception exception) {
            objectNode.put("error", exception.getMessage());
            output.add(this.objectNode);

        }
        this.objectNode = null;

    }
}
