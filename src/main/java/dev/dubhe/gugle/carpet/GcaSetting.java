package dev.dubhe.gugle.carpet;

import dev.dubhe.curtain.api.rules.Rule;
import dev.dubhe.curtain.api.rules.Validators;

import static dev.dubhe.curtain.api.rules.Categories.BOT;
import static dev.dubhe.curtain.api.rules.Categories.COMMAND;

public class GcaSetting {

    public static final String GCA = "GCA";
    public static final String EXPERIMENTAL = "experimental";

    @Rule(
        categories = {GCA, BOT, EXPERIMENTAL},
        suggestions = {"true", "false", "ops", "0", "1", "2", "3", "4"},
        validators = Validators.CommandLevel.class,
        serializedName = "openRealPlayerInventory"
    )
    public static String openRealPlayerInventory = "false";

    @Rule(
        categories = {GCA, BOT, EXPERIMENTAL},
        suggestions = {"spawn", "death", "setting", "false"},
        serializedName = "fakePlayerAutoRespawn"
    )
    public static String fakePlayerAutoRespawn = "false";

    @Rule(
        categories = {GCA, BOT, COMMAND},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandBot"
    )
    public static String commandBot = "ops";

    @Rule(
        categories = {GCA, BOT, COMMAND},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandBotAction"
    )
    public static String commandBotAction = "ops";

    @Rule(
        categories = {GCA, BOT, COMMAND, EXPERIMENTAL},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandBotController"
    )
    public static String commandBotController = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandTodo"
    )
    public static String commandTodo = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandHere"
    )
    public static String commandHere = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandWhereis"
    )
    public static String commandWhereis = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        suggestions = {"ops", "0", "1", "2", "3", "4", "true", "false"},
        serializedName = "commandLoc"
    )
    public static String commandLoc = "ops";

    @Rule(
        categories = {GCA, COMMAND},
        serializedName = "commandWlist"
    )
    public static boolean commandWlist = false;

    @Rule(
        categories = {GCA, COMMAND},
        serializedName = "commandBlist"
    )
    public static boolean commandBlist = false;

    @Rule(
        categories = {GCA, COMMAND},
        serializedName = "commandSop"
    )
    public static boolean commandSop = false;

    @Rule(
        categories = {GCA},
        suggestions = {"vanilla", "true", "false", "ops", "0", "1", "2", "3", "4"},
        validators = GcaValidators.CommandLevelWithVanilla.class,
        serializedName = "commandSeed"
    )
    public static String commandSeed = "vanilla";

    @Rule(
        categories = {GCA},
        serializedName = "betterItemFrameInteraction"
    )
    public static boolean betterItemFrameInteraction = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL},
        serializedName = "betterQuickCrafting"
    )
    public static boolean betterQuickCrafting = false;

    @Rule(
        categories = {GCA},
        serializedName = "simpleInGameCalculator"
    )
    public static boolean simpleInGameCalculator = false;

    @Rule(
        categories = {GCA},
        serializedName = "fastPingFriend"
    )
    public static boolean fastPingFriend = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL},
        serializedName = "qnmdLC"
    )
    public static int qnmdLC = -1;

    @Rule(
        categories = {GCA, EXPERIMENTAL},
        serializedName = "fixedEndCrystalSync"
    )
    public static boolean fixedEndCrystalSync = false;

    @Rule(
        categories = {GCA},
        serializedName = "welcomePlayer"
    )
    public static boolean welcomePlayer = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL},
        serializedName = "wanderingTraderSpawnFailedWarning"
    )
    public static boolean wanderingTraderSpawnFailedWarning = false;

    @Rule(
        categories = {GCA, EXPERIMENTAL},
        serializedName = "wanderingTraderSpawnRemind"
    )
    public static boolean wanderingTraderSpawnRemind = false;

    @Rule(
        categories = {GCA},
        validators = GcaValidators.PositiveNumber.class,
        suggestions = {"8"},
        serializedName = "gcaPageSize"
    )
    public static int gcaPageSize = 8;

}