package dev.dubhe.gugle.carpet;

import dev.dubhe.curtain.api.rules.CurtainRule;
import dev.dubhe.curtain.api.rules.IValidator;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class GcaValidators {
    public static final boolean CARPET_AMS_ADDITION = ModList.get().isLoaded("carpet-ams-addition");

    public static class CommandLevelWithVanilla implements IValidator<String> {
        public static final List<String> OPTIONS = List.of("vanilla", "true", "false", "ops", "0", "1", "2", "3", "4");

        @Override
        public boolean validate(CommandSourceStack source, CurtainRule<String> rule, String newValue) {
            return OPTIONS.contains(newValue);
        }
    }

    public static class PositiveNumber implements IValidator<Integer> {
        @Override
        public boolean validate(CommandSourceStack source, CurtainRule<Integer> rule, String newValue) {
            try {
                return Integer.parseInt(newValue) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
