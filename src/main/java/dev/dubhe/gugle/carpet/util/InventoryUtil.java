package dev.dubhe.gugle.carpet.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.EquipmentSlot;
import java.util.Optional;

public class InventoryUtil {
    public static NonNullList<ItemStack> getItems(Player player) {
        return player.getInventory().items;
    }

    public static NonNullList<ItemStack> getArmor(Player player) {
        return player.getInventory().armor;
    }

    public static NonNullList<ItemStack> getOffHand(Player player) {
        return player.getInventory().offhand;
    }

    public static int getSelected(Player player) {
        return player.getInventory().selected;
    }

    public static boolean hasMendingEnchant(ItemStack itemStack) {
        return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MENDING, itemStack) > 0;
    }

    public static Optional<EquipmentSlot> getEquipmentSlot(Player fakePlayer, ItemStack itemStack) {
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (fakePlayer.getItemBySlot(equipmentSlot) == itemStack) {
                return Optional.of(equipmentSlot);
            }
        }
        return Optional.empty();
    }
}
