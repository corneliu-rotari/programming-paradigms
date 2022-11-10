package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.cards.card.Card;
import main.mechanics.player.Player;
import main.mechanics.table.GameTable;
import main.util.GameConstants;

import java.util.ArrayList;

public final class DebugAndStatsCommands {
    private ObjectMapper mapper = new ObjectMapper();
    private GameTable gameTable;

    public DebugAndStatsCommands(GameTable gameTable) {
        this.gameTable = gameTable;
    }




}
