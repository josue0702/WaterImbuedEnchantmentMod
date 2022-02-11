package com.theshire.waterimbuedenchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class ThunderImbuedEnchantment extends Enchantment {
    public ThunderImbuedEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 5;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (!Main.SETTINGS.tieMendingCompatible) {
            return this != other && other != Enchantments.MENDING && other != Main.WIE && other != Enchantments.RIPTIDE;
        } else {
            return this != other && other != Main.WIE && other != Enchantments.RIPTIDE;
        }
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return Main.SETTINGS.tieVillagerSold;
    }

    @Override
    public boolean isAvailableForRandomSelection() { return Main.SETTINGS.tieTreasure; }
}
