package com.theshire.waterimbuedenchantment.mixin;

import com.theshire.waterimbuedenchantment.WaterImbuedEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.level.LevelInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(TridentItem.class)
public class MixinTridentItem {

    @Inject(method = "releaseUsing", at = @At("HEAD"))
    public void releaseUsing(ItemStack itemStack, LevelInfo level, LivingEntity livingEntity, int integer, CallbackInfo callbackInfo){
        boolean hasWie = false;

        Iterator<NbtElement> i = itemStack.getEnchantments().iterator();

        while (i.hasNext()){
            if (i.next() instanceof WaterImbuedEnchantment) hasWie = true;
        }

        System.out.println(hasWie);
    }
}
