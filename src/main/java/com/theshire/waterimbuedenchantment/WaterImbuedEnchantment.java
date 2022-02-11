package com.theshire.waterimbuedenchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class WaterImbuedEnchantment extends Enchantment {
    public WaterImbuedEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 5;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (!Main.SETTINGS.wieMendingCompatible) {
            return this != other && other != Enchantments.MENDING && other != Enchantments.CHANNELING;
        } else {
            return this != other && other != Enchantments.CHANNELING;
        }
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Main.SETTINGS.wieVillagerSold;
    }

    @Override
    public boolean isAvailableForRandomSelection() { return Main.SETTINGS.wieTreasure; }
}
