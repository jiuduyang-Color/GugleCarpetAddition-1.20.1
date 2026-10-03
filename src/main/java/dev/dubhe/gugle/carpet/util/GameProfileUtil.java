package dev.dubhe.gugle.carpet.util;

import carpet.CarpetSettings;
import com.mojang.authlib.GameProfile;
import dev.dubhe.gugle.carpet.GcaSetting;
import net.minecraft.core.UUIDUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.GameProfileCache;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.world.level.block.entity.SkullBlockEntity;

public class GameProfileUtil {

    @Nullable
    public static GameProfile getGameProfile(MinecraftServer server, final String name) {
        GameProfile gameprofile = null;
        if (!GcaSetting.fakePlayerForceOfflineUUID) {
            GameProfileCache.setUsesAuthentication(false);
            try {
                GameProfileCache cache = server.getProfileCache();
                if (cache != null) {
                    gameprofile = cache.get(name).orElse(null);
                }
            } finally {
                GameProfileCache.setUsesAuthentication(server.isDedicatedServer() && server.usesAuthentication());
            }
        }
        if (gameprofile == null && CarpetSettings.allowSpawningOfflinePlayers) {
            gameprofile = new GameProfile(UUIDUtil.createOfflinePlayerUUID(name), name);
        }
        if (gameprofile != null && !GcaSetting.fakePlayerForceOfflineUUID && gameprofile.getProperties().containsKey("textures")) {
            AtomicReference<GameProfile> result = new AtomicReference<>();
            SkullBlockEntity.updateGameprofile(gameprofile, result::set);
            gameprofile = result.get();
        }
        return gameprofile;
    }

}
