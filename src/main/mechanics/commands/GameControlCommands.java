package main.mechanics.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import fileio.Coordinates;
import main.cards.card.character.hero.HeroCard;
import main.mechanics.table.GameTable;
import main.util.Const;

public final class GameControlCommands implements CommandControlHelper {
    private ObjectNode objectNode;
    private ObjectMapper mapper;
    private ArrayNode output;
    private GameTable gameTable;

    public GameControlCommands(final ArrayNode output) {
        this.gameTable = GameTable.getGameTable();
        this.output = output;
        this.mapper = new ObjectMapper();

    }

    /**
     * C
     * @param actionsInput - command information
     */
    public void determineCommand(final ActionsInput actionsInput) {
        this.objectNode = mapper.createObjectNode();
        this.objectNode.put("command", actionsInput.getCommand());
        try {
            switch (actionsInput.getCommand()) {
                case Const.END_PLAYER_TURN -> this.gameTable.endPlayerTurn();
                case Const.PLACE_CARD -> {
                    this.objectNode.put("handIdx", actionsInput.getHandIdx());
                    this.gameTable.getCardTable().placeCard(actionsInput.getHandIdx());
                }
                case Const.CARD_USES_ATTACK -> {
                    Coordinates attacker = actionsInput.getCardAttacker();
                    Coordinates attacked = actionsInput.getCardAttacked();

                    outputCordJSON(attacker, attacked);

                    this.gameTable.getCardTable().attackCard(attacker, attacked);
                }
                case Const.USE_ENV_CARD -> {
                    this.objectNode.put("handIdx", actionsInput.getHandIdx());
                    this.objectNode.put("affectedRow", actionsInput.getAffectedRow());
                    this.gameTable.getOffensivePlayer().
                            useEnvironmentCard(actionsInput.getHandIdx(),
                                                actionsInput.getAffectedRow());
                }
                case Const.CARD_USES_ABILITY -> {
                    Coordinates attacker = actionsInput.getCardAttacker();
                    Coordinates attacked = actionsInput.getCardAttacked();

                    outputCordJSON(attacker, attacked);

                    this.gameTable.getCardTable().useAbility(attacker, attacked);
                }
                case Const.USE_ATTACK_ON_HERO -> {
                    HeroCard hero = null;
                    try {
                        hero = this.gameTable.getCardTable().
                                attackHero(actionsInput.getCardAttacker());
                        if (hero.isHeroDead()) {
                            this.objectNode = mapper.createObjectNode();
                            if (this.gameTable.getPlayerTurn() == Const.PLAYER_ONE) {
                                this.objectNode.put("gameEnded",
                                        "Player one killed the enemy hero.");
                            } else {
                                this.objectNode.put("gameEnded",
                                        "Player two killed the enemy hero.");
                            }
                            this.output.add(this.objectNode);
                            this.gameTable.endGame();
                        }
                    } catch (Exception exception) {
                        this.objectNode.set("cardAttacker", mapper.
                                valueToTree(actionsInput.getCardAttacker()));
                        throw exception;
                    }
                }
                case Const.USE_HERO_ABILITY -> {
                    this.objectNode.put("affectedRow", actionsInput.getAffectedRow());
                    this.gameTable.getCardTable().useHeroAbility(actionsInput.getAffectedRow());
                }
                default -> {
                }
            }

        } catch (Exception exception) {
            objectNode.put("error", exception.getMessage());
            output.add(this.objectNode);

        }
        this.objectNode = null;
        GameTable.getGameTable().getCardTable().checkCardsHealth();

    }

    private void outputCordJSON(final Coordinates attacker, final Coordinates attacked) {
        this.objectNode.set("cardAttacker", mapper.valueToTree(attacker));
        this.objectNode.set("cardAttacked", mapper.valueToTree(attacked));
    }

}
