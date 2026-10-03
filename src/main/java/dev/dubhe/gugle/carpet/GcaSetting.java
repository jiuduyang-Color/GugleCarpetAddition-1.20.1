package dev.dubhe.gugle.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.Validators;

public class GcaSetting {

    public static final String GCA = "GCA";
    public static final String EXPERIMENTAL = "experimental";
    public static final String BOT = "BOT";
    public static final String COMMAND = "command";

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean openFakePlayerInventory = false;

    @Rule(
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"},
        categories = {GCA, EXPERIMENTAL},
        validators = Validators.CommandLevel.class
    )
    public static String openRealPlayerInventory = "false";

    @Rule(
        categories = {GCA, BOT},
        options = {"ender_chest", "true", "false"},
        validators = GcaValidators.EnderChest.class
    )
    public static String openFakePlayerEnderChest = "false";

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerResident = false;

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerReloadAction = true;

    @Rule(
        categories = {GCA, BOT, EXPERIMENTAL},
        options = {"spawn", "death", "setting", "false"}
    )
    public static String fakePlayerAutoRespawn = "false";

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerAutoReplenishment = false;

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerAutoReplenishmentFormShulkerBox = false;

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerAutoFish = false;

    @Rule(
        options = {"false", "true", "keep"},
        categories = {GCA, BOT}
    )
    public static String fakePlayerAutoReplaceTool = "false";

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerToolDamagedNotification = false;

    public static final String fakePlayerNoneName = "#none";

    @Rule(
        options = {fakePlayerNoneName, "bot_"},
        categories = {GCA, BOT}
    )
    public static String fakePlayerPrefixName = fakePlayerNoneName;

    @Rule(
        options = {fakePlayerNoneName, "_fake"},
        categories = {GCA, BOT}
    )
    public static String fakePlayerSuffixName = fakePlayerNoneName;

    @Rule(
        categories = {GCA, BOT}
    )
    public static boolean fakePlayerForceOfflineUUID = false;

    @Rule(
        categories = {GCA, BOT, COMMAND},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"}
    )
    public static String commandBot = "ops";

    @Rule(
        categories = {GCA, BOT, COMMAND},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"}
    )
    public static String commandBotAction = "ops";

    @Rule(
        categories = {GCA, BOT, COMMAND, EXPERIMENTAL},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"}
    )
    public static String commandBotController = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"}
    )
    public static String commandTodo = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        conditions = GcaValidators.CarpetAmsAdditionLoaded.class
    )
    public static String commandHere = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"}
    )
    public static String commandWhereis = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        options = {"ops", "0", "1", "2", "3", "4", "true", "false"}
    )
    public static String commandLoc = "ops";

    @Rule(
        categories = {GCA, COMMAND}
    )
    public static boolean commandWlist = false;

    @Rule(
        categories = {GCA, COMMAND}
    )
    public static boolean commandBlist = false;

    @Rule(
        categories = {GCA, COMMAND}
    )
    public static boolean commandSop = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean betterFenceGatePlacement = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean betterWoodStrip = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean betterSignInteraction = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean betterItemFrameInteraction = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL}
    )
    public static boolean betterQuickCrafting = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean simpleInGameCalculator = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean fastPingFriend = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL}
    )
    public static int qnmdLC = -1;

    @Rule(
        categories = {GCA, EXPERIMENTAL}
    )
    public static boolean fixedEndCrystalSync = false;

    @Rule(
        categories = {GCA}
    )
    public static boolean welcomePlayer = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL}
    )
    public static boolean wanderingTraderSpawnFailedWarning = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL}
    )
    public static boolean wanderingTraderSpawnRemind = false;

    @Rule(
        options = {"vanilla", "true", "false", "ops", "0", "1", "2", "3", "4"},
        categories = {GCA, COMMAND},
        validators = GcaValidators.CommandLevelWithVanilla.class
    )
    public static String commandSeed = "vanilla";

    @Rule(
        categories = {GCA},
        validators = GcaValidators.PositiveNumber.class,
        options = {"8"},
        strict = false
    )
    public static int gcaPageSize = 8;

}
