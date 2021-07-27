package com.theshire.waterimbuedenchantment.mixin;

import com.theshire.waterimbuedenchantment.Main;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

import static net.minecraft.enchantment.EnchantmentHelper.getLevel;

@Mixin(TridentItem.class)
public class TridentItemMixin extends Item implements Vanishable {

    public TridentItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "use", at = @At("HEAD"))
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack itemStack = user.getStackInHand(hand);
        int hasWie = getLevel(Main.WIE, itemStack);

        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else if (EnchantmentHelper.getRiptide(itemStack) > 0 && !user.isTouchingWaterOrRain() && hasWie <= 0) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Inject(method = "onStoppedUsing", at = @At("RETURN"))
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci){
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= 10) {
                int j = EnchantmentHelper.getRiptide(stack);
                int wie = getLevel(Main.WIE, stack);
                if (j > 0 && !playerEntity.isTouchingWaterOrRain() && wie > 0) {
                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

                    stack.damage(1, (LivingEntity)playerEntity, (Consumer<LivingEntity>)((p) -> {
                        p.sendToolBreakStatus(user.getActiveHand());
                    }));

                    float f = playerEntity.getYaw();
                    float g = playerEntity.getPitch();
                    float h = -MathHelper.sin(f * 0.017453292F) * MathHelper.cos(g * 0.017453292F);
                    float k = -MathHelper.sin(g * 0.017453292F);
                    float l = MathHelper.cos(f * 0.017453292F) * MathHelper.cos(g * 0.017453292F);
                    float m = MathHelper.sqrt(h * h + k * k + l * l);
                    float n = 3.0F * ((1.0F + (float) j) / 4.0F);
                    h *= n / m;
                    k *= n / m;
                    l *= n / m;
                    playerEntity.addVelocity((double) h, (double) k, (double) l);
                    playerEntity.setRiptideTicks(20);
                    if (playerEntity.isOnGround()) {
                        float o = 1.1999999F;
                        playerEntity.move(MovementType.SELF, new Vec3d(0.0D, 1.1999999284744263D, 0.0D));
                    }

                    SoundEvent soundEvent3;
                    if (j >= 3) {
                        soundEvent3 = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;
                    } else if (j == 2) {
                        soundEvent3 = SoundEvents.ITEM_TRIDENT_RIPTIDE_2;
                    } else {
                        soundEvent3 = SoundEvents.ITEM_TRIDENT_RIPTIDE_1;
                    }

                    world.playSoundFromEntity((PlayerEntity) null, playerEntity, soundEvent3, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }
}