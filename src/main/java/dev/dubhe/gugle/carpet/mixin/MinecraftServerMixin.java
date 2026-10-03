package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.tools.player.FakePlayerAutoRespawn;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "stopServer", at = @At("HEAD"))
    public void clearAutoRespawn(CallbackInfo ci) {
        FakePlayerAutoRespawn.clear();
    }
}