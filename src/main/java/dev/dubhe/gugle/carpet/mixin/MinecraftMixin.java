package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.config.updater.ConfigUpdater;
import net.minecraft.client.Minecraft;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "doWorldLoad", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;spin(Ljava/util/function/Function;)Lnet/minecraft/server/MinecraftServer;"))
    private void updateConfig(
        String string,
        LevelStorageSource.LevelStorageAccess levelStorageAccess, PackRepository packRepository, WorldStem worldStem,
        boolean bl, CallbackInfo ci
        , @Local Services services
        ) {
        ConfigUpdater.tryUpdateOldVersion(levelStorageAccess, services, false);
    }

}
