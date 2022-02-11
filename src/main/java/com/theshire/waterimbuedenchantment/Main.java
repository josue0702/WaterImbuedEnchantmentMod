package com.theshire.waterimbuedenchantment;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {

	public static final String MOD_ID = "wie";
	public static Settings SETTINGS = new Settings();

	public static final Enchantment WIE = new WaterImbuedEnchantment(
			Enchantment.Rarity.UNCOMMON,
			EnchantmentTarget.TRIDENT,
			new EquipmentSlot[]{
					EquipmentSlot.MAINHAND
			}
	);

	public static final Enchantment TIE = new ThunderImbuedEnchantment(
			Enchantment.Rarity.UNCOMMON,
			EnchantmentTarget.TRIDENT,
			new EquipmentSlot[]{
					EquipmentSlot.MAINHAND
			}
	);

	@Override
	public void onInitialize() {
		AutoConfig.register(Settings.class, JanksonConfigSerializer::new);
		SETTINGS = AutoConfig.getConfigHolder(Settings.class).getConfig();

		Registry.register(Registry.ENCHANTMENT, new Identifier("wie", "water_imbued_enchantment"), WIE);
		Registry.register(Registry.ENCHANTMENT, new Identifier("wie", "thunder_imbued_enchantment"), TIE);
	}
}
