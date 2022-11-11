package main.util.constants;

public interface CommandsConstants {
//    Debug and Stats parameter
    String GET_PLAYER_DECK = "getPlayerDeck";
    String GET_CARDS_IN_HAND = "getCardsInHand";
    String GET_PLAYER_HERO = "getPlayerHero";
    String GET_PLAYER_MANA = "getPlayerMana";
    String GET_ENV_CARD_IN_HAND = "getEnvironmentCardsInHand";
    String GET_CARD_AT_POS = "getCardAtPosition";
    String GET_PLAYER_TURN = "getPlayerTurn";
    String GET_FROZEN_CARDS_ON_TABLE = "getFrozenCardsOnTable";
    String GET_TOTAL_GAMES_PLAYED = "getTotalGamesPlayed";
    String GET_PLAYER_ONE_WINS = "getPlayerOneWins";
    String GET_PLAYER_TWO_WINS = "getPlayerTwoWins";
    String GET_CARDS_ON_TABLE = "getCardsOnTable";

//    Gameplay commands
    String END_PLAYER_TURN = "endPlayerTurn";
    String PLACE_CARD = "placeCard";
    String CARD_USES_ATTACK = "cardUsesAttack";
    String CARD_USES_ABILITY = "cardUsesAbility";
    String USE_ATTACK_ON_HERO = "useAttackHero";
    String USE_HERO_ABILITY = "useHeroABILITY";
    String USE_ENV_CARD = "useEnvironmentCard";

}
