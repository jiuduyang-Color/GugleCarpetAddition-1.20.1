package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.curtain.features.player.fakes.IServerPlayer;
import dev.dubhe.curtain.features.player.helpers.EntityPlayerActionPack;
import dev.dubhe.curtain.features.player.patches.EntityPlayerMPFake;
import com.mojang.authlib.GameProfile;
import dev.dubhe.gugle.carpet.GcaSetting;
import dev.dubhe.gugle.carpet.tools.player.FakePlayerAutoRespawn;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerMPFake.class)
public abstract class EntityPlayerMPFakeMixin extends ServerPlayer {
    public EntityPlayerMPFakeMixin(MinecraftServer minecraftServer, ServerLevel serverLevel, GameProfile gameProfile) {
        super(minecraftServer, serverLevel, gameProfile);
    }

    @Inject(method = "die", at = @At("HEAD"))
    private void onDie(DamageSource cause, CallbackInfo ci) {
        this.gca$cacheActionPack();
    }

    @Inject(method = "kill(Lnet/minecraft/network/chat/Component;)V", remap = false, at = @At("HEAD"))
    private void onKill(Component reason, CallbackInfo ci) {
        this.gca$cacheActionPack();
    }

    private void gca$cacheActionPack() {
        if ("false".equals(GcaSetting.fakePlayerAutoRespawn)) return;
        EntityPlayerActionPack pack = ((IServerPlayer) this).getActionPack();
        FakePlayerAutoRespawn.onFakePlayerDied(this.uuid, pack);
    }
}