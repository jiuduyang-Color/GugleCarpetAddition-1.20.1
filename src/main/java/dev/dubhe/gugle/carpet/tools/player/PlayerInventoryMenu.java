package dev.dubhe.gugle.carpet.tools.player;

import dev.dubhe.gugle.carpet.mixin.AbstractContainerMenuAccessor;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraft.world.item.ArmorItem;

public class PlayerInventoryMenu extends ChestMenu {
    public PlayerInventoryMenu(int i, Inventory inventory, Container container) {
        super(MenuType.GENERIC_9x6, i, inventory, container, 6);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return quickMove(this, slotIndex);
    }

    public static ItemStack quickMove(ChestMenu chestMenu, int slotIndex) {
        ItemStack remainingItem = ItemStack.EMPTY;
        Slot slot = chestMenu.slots.get(slotIndex);
        int ordinal;
        if (slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            remainingItem = slotStack.copy();
            if (slotIndex < 54) {
                AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) (chestMenu);
                if (!accessor.invokeMoveItemStackTo(slotStack, 54, chestMenu.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if ((ordinal = getArmorOrdinal(slotStack)) >= 0) {

                if (PlayerInventoryMenu.moveToArmor(chestMenu, slotStack, ordinal) || moveToInventory(chestMenu, slotStack)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotStack.is(Items.ELYTRA)) {

                if (PlayerInventoryMenu.moveToArmor(chestMenu, slotStack, 1) || moveToInventory(chestMenu, slotStack)) {
                    return ItemStack.EMPTY;
                }
            } else if (
                slotStack.getItem().isEdible()
            ) {

                if (PlayerInventoryMenu.moveToOffHand(chestMenu, slotStack) || moveToInventory(chestMenu, slotStack)) {
                    return ItemStack.EMPTY;
                }
            } else if (moveToInventory(chestMenu, slotStack)) {

                AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) (chestMenu);
                if (accessor.invokeMoveItemStackTo(slotStack, 1, 8, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }
            if (slotStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return remainingItem;
    }

    private static int getArmorOrdinal(ItemStack stack) {
        int ordinal = -1;
        Item item = stack.getItem();
        if (item instanceof ArmorItem armorItem) {
            ordinal = armorItem.getType().ordinal();
        }
        return ordinal;
    }

    private static boolean moveToOffHand(ChestMenu chestMenu, ItemStack slotStack) {
        AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) (chestMenu);
        return accessor.invokeMoveItemStackTo(slotStack, 7, 8, false);
    }

    private static boolean moveToArmor(ChestMenu chestMenu, ItemStack slotStack, int ordinal) {
        AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) (chestMenu);
        return accessor.invokeMoveItemStackTo(slotStack, ordinal + 1, ordinal + 2, false);
    }

    private static boolean moveToInventory(ChestMenu chestMenu, ItemStack slotStack) {
        AbstractContainerMenuAccessor accessor = (AbstractContainerMenuAccessor) (chestMenu);
        return !accessor.invokeMoveItemStackTo(slotStack, 18, 54, false);
    }
}
