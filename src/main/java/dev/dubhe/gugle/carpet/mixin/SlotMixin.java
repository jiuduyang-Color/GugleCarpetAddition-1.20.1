package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.tools.player.ISlotIcon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.inventory.InventoryMenu;

@Mixin(Slot.class)
abstract class SlotMixin implements ISlotIcon {
    @Unique
    private Pair<ResourceLocation, ResourceLocation> pair;

    @Inject(method = "getNoItemIcon", at = @At("HEAD"), cancellable = true)
    private void getNoItemIcon(
        CallbackInfoReturnable<Pair<ResourceLocation, ResourceLocation>> cir
    ) {
        if (this.pair != null) cir.setReturnValue(this.pair);
    }

    @Override
    @SuppressWarnings("AddedMixinMembersNamePattern")
    public void setIcon(ResourceLocation resource) {
        if (resource != null) {
            this.pair = Pair.of(InventoryMenu.BLOCK_ATLAS, resource);
        }
    }
}
