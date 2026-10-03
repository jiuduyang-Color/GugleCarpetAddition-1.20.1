package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.curtain.features.player.patches.EntityPlayerMPFake;
import dev.dubhe.gugle.carpet.tools.player.FakePlayerAutoRespawn;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Inject(method = "placeNewPlayer", at = @At("HEAD"))
    private void onFakePlayerSpawned(Connection connection, ServerPlayer serverPlayer,
                                     CallbackInfo ci) {
        if (serverPlayer instanceof EntityPlayerMPFake) {
            FakePlayerAutoRespawn.onFakePlayerSpawned(serverPlayer.getUUID());
        }
    }
}
