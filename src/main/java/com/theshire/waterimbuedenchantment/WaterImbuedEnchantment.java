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
        return 10 + 20 * (level + 2);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return this != other && other != Enchantments.MENDING && other != Enchantments.CHANNELING;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() { return false; }
}
