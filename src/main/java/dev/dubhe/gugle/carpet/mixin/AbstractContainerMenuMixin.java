package dev.dubhe.gugle.carpet.mixin;

import dev.dubhe.gugle.carpet.api.menu.control.Button;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.nbt.Tag;
import net.minecraft.world.inventory.ClickType;

@Mixin(AbstractContainerMenu.class)
abstract class AbstractContainerMenuMixin {
    @Unique
    private final AbstractContainerMenu gca$self = (AbstractContainerMenu) (Object) this;

    @Inject(method = "doClick", at = @At("HEAD"), cancellable = true)
    private void doClick(int slotIndex, int button,
                         ClickType
                             clickType, Player player, CallbackInfo ci) {
        if (slotIndex < 0) return;
        Slot slot = gca$self.getSlot(slotIndex);
        ItemStack itemStack = slot.getItem();
        if (itemStack.getTag() == null) {
            return;
        }
        Tag tag = itemStack.getTag().get(Button.GCA_CLEAR);
        if (tag == null || !itemStack.getTag().getBoolean(Button.GCA_CLEAR)) {
            return;
        }
        itemStack.setCount(0);
        ci.cancel();
    }
}
