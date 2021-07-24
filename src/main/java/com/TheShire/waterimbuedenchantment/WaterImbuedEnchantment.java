package com.theshire.waterimbuedenchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class WaterImbuedEnchantment extends Enchantment {

    protected WaterImbuedEnchantment(Enchantment.Rarity rarity, EnchantmentTarget target, EquipmentSlot[] equipmentSlots){
        super(rarity, target, equipmentSlots);
    }
}
