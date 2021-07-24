package com.theshire.waterimbuedenchantment;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {

	private static final Enchantment wie = new WaterImbuedEnchantment(
			Enchantment.Rarity.VERY_RARE,
			EnchantmentTarget.TRIDENT,
			new EquipmentSlot[]{
					EquipmentSlot.MAINHAND
			}
			);

	@Override
	public void onInitialize() {
		Registry.register(Registry.ENCHANTMENT, new Identifier("wie", "water_imbued_enchantment"), wie);
	}
}
