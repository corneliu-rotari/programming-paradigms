package implementation.utils;

import implementation.utils.constants.CommandsConstants;
import implementation.utils.constants.EnvironmentCardConstants;
import implementation.utils.constants.HeroCardConst;
import implementation.utils.constants.MinionCardConstants;

/**
 * Class of constants used for developments
 */
public final class Const implements
        HeroCardConst, MinionCardConstants, EnvironmentCardConstants, CommandsConstants {
    private Const() {
    }
/*
Game Constants
 */
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_ONE_BACK = 3;
    public static final int PLAYER_ONE_FRONT = 2;
    public static final int PLAYER_TWO = 2;
    public static final int PLAYER_TWO_BACK = 0;
    public static final int PLAYER_TWO_FRONT = 1;
    public static final int NR_TABLE_ROWS = 4;
    public static final int NR_TABLE_COLUMNS = 5;
    public static final int MAX_MANA = 10;

}
