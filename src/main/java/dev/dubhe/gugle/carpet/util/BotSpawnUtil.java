package dev.dubhe.gugle.carpet.util;

import dev.dubhe.curtain.features.player.helpers.EntityPlayerActionPack;
import dev.dubhe.curtain.features.player.patches.EntityPlayerMPFake;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dubhe.gugle.carpet.GcaExtension;
import dev.dubhe.gugle.carpet.entry.BotExecutorInfo;
import dev.dubhe.gugle.carpet.entry.BotInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class BotSpawnUtil {
    public static boolean spawnBot(MinecraftServer server, BotInfo bot) {
        return spawnBot(server, bot, true, null);
    }

    public static boolean spawnBot(MinecraftServer server, BotInfo bot, boolean applyAction, @Nullable EntityPlayerActionPack actionPack) {
        ServerLevel level = server.getLevel(bot.dimension());
        if (level == null) return false;

        Vec2 facing = bot.facing() == null ? Vec2.ZERO : bot.facing();
        Vec3 pos = bot.pos();
        if (pos == null) {
            BlockPos spawn = level.getSharedSpawnPos();
            pos = new Vec3(spawn.getX() + 0.5D, spawn.getY(), spawn.getZ() + 0.5D);
        }

        EntityPlayerMPFake instance = EntityPlayerMPFake.createFakePlayer(
            bot.name(),
            server,
            pos.x,
            pos.y,
            pos.z,
            facing.y,
            facing.x,
            bot.dimension(),
            bot.mode(),
            bot.flying()
        );
        if (instance == null) return false;

        if (applyAction) applyAction(server, instance, bot, actionPack);
        return true;
    }

    private static void applyAction(MinecraftServer server, EntityPlayerMPFake instance, BotInfo bot, @Nullable EntityPlayerActionPack actionPack) {
        List<BotExecutorInfo> startups = bot.getStartups();
        if (startups.isEmpty()) {
            bot.actions().applyAction(instance, actionPack);
            return;
        }

        for (BotExecutorInfo startup : startups) {
            try {
                server.getCommands().getDispatcher().execute(
                    startup.command(bot.name()).substring(1),
                    instance.createCommandSourceStack()
                );
            } catch (CommandSyntaxException e) {
                GcaExtension.LOGGER.warn("Failed to execute startup action {} for bot {}: {}", startup.desc(), bot.name(), e.getMessage());
            }
        }
    }

}