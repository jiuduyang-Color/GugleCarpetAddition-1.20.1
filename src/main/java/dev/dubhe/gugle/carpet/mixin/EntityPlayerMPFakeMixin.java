package dev.dubhe.gugle.carpet.mixin;

import carpet.fakes.ServerPlayerInterface;
import carpet.helpers.EntityPlayerActionPack;
import carpet.patches.EntityPlayerMPFake;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import dev.dubhe.gugle.carpet.GcaSetting;
import dev.dubhe.gugle.carpet.tools.player.FakePlayerAutoReplaceTool;
import dev.dubhe.gugle.carpet.tools.player.FakePlayerAutoRespawn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

import java.util.Optional;
import com.mojang.authlib.properties.PropertyMap;

@Mixin(EntityPlayerMPFake.class)
public abstract class EntityPlayerMPFakeMixin extends ServerPlayer {
    public EntityPlayerMPFakeMixin(MinecraftServer minecraftServer, ServerLevel serverLevel, GameProfile gameProfile
    ) {
        super(minecraftServer, serverLevel, gameProfile
        );
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void doTick(CallbackInfo ci) {
        if (!"false".equals(GcaSetting.fakePlayerAutoReplaceTool)) {
            FakePlayerAutoReplaceTool.tryReplaceTool(this);
        }
    }

    @Inject(method = "die", at = @At("HEAD"))
    private void onDie(DamageSource cause, CallbackInfo ci) {
        if ("false".equals(GcaSetting.fakePlayerAutoRespawn)) return;
        EntityPlayerActionPack pack = ((ServerPlayerInterface) this).getActionPack();
        FakePlayerAutoRespawn.onFakePlayerDied(this.uuid, pack);
    }

    @Nullable
    @WrapOperation(method = "createFake", remap = false, at = @At(value = "INVOKE", target = "Ljava/util/Optional;orElse(Ljava/lang/Object;)Ljava/lang/Object;"))
    private static <T> T useOfflineUUID(Optional<T> instance, T other, Operation<T> original) {
        if (GcaSetting.fakePlayerForceOfflineUUID) return null;
        return original.call(instance, other);
    }

    @WrapOperation(method = "createFake", at = @At(value = "INVOKE", target = "Lcom/mojang/authlib/properties/PropertyMap;containsKey(Ljava/lang/Object;)Z"), remap = false)
    private static boolean cancelFetchUUID(PropertyMap instance, Object o, Operation<Boolean> original) {
        if (GcaSetting.fakePlayerForceOfflineUUID) {
            return false;
        }
        return original.call(instance, o);
    }
}
