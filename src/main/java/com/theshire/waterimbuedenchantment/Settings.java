package com.theshire.waterimbuedenchantment;

import me.lortseam.completeconfig.api.ConfigEntry;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "wie")
public class Settings implements ConfigData {
    @me.shedaniel.autoconfig.annotation.ConfigEntry.Category("wiecat")
    @ConfigEntry
    public boolean wieMendingCompatible = false;

    @me.shedaniel.autoconfig.annotation.ConfigEntry.Category("wiecat")
    @Comment("Can spawn on world treasure chest and is available to the enchanting table")
    @ConfigEntry
    public boolean wieTreasure = false;

    @me.shedaniel.autoconfig.annotation.ConfigEntry.Category("wiecat")
    @ConfigEntry
    public boolean wieVillagerSold = false;

    @me.shedaniel.autoconfig.annotation.ConfigEntry.Category("tiecat")
    @ConfigEntry
    public boolean tieMendingCompatible = false;

    @me.shedaniel.autoconfig.annotation.ConfigEntry.Category("tiecat")
    @Comment("Can spawn on world treasure chest and is available to the enchanting table")
    @ConfigEntry
    public boolean tieTreasure = false;

    @me.shedaniel.autoconfig.annotation.ConfigEntry.Category("tiecat")
    @ConfigEntry
    public boolean tieVillagerSold = false;
}
