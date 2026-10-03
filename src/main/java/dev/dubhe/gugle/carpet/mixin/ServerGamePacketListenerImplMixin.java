package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.curtain.features.player.patches.EntityPlayerMPFake;
import dev.dubhe.gugle.carpet.GcaSetting;
import dev.dubhe.gugle.carpet.entry.TriConsumer;
import dev.dubhe.gugle.carpet.tools.FastPingFriend;
import dev.dubhe.gugle.carpet.tools.SimpleInGameCalculator;
import dev.dubhe.gugle.carpet.tools.player.FakePlayerAutoRespawn;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
abstract class ServerGamePacketListenerImplMixin {

    @Shadow
    public abstract ServerPlayer getPlayer();

    @Inject(method = "onDisconnect", at = @At("TAIL"))
    private void onDisconnect(Component details, CallbackInfo ci) {
        if (this.getPlayer() instanceof EntityPlayerMPFake fakePlayer) {
            FakePlayerAutoRespawn.tryRespawn(fakePlayer);
        }
    }

    @Inject(method = "broadcastChatMessage", at = @At("TAIL"))
    private void handleChat(PlayerChatMessage playerChatMessage, CallbackInfo ci) {
        Component component = playerChatMessage.decoratedContent();
        this.gca$handleChat(GcaSetting.simpleInGameCalculator, "==", component, (server, player, msg) -> SimpleInGameCalculator.handleChat(server, msg));
        this.gca$handleChat(GcaSetting.fastPingFriend, "@ ", component, FastPingFriend::handleChat);
        this.gca$handleChat(GcaSetting.fastPingFriend, "@@ ", component, FastPingFriend::handleChatUrgent);
    }

    @Unique
    private void gca$handleChat(boolean rule, String prefix, Component component, TriConsumer<MinecraftServer, ServerPlayer, String> handle) {
        if (!rule) return;
        String string = component.getString();
        if (!string.startsWith(prefix)) return;
        string = string.substring(prefix.length());
        ServerPlayer player = this.getPlayer();
        handle.accept(player.level().getServer(), player, string);
    }
}
