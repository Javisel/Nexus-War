package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.AbilityItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FightThroughThePain extends AbilityItem {
    public FightThroughThePain() {
        super("gunslinger_ability_fightthroughthepain", 20*60*5,0);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.initializeItem(stack);

        if (entityIn instanceof EntityPlayer) {
            if (!isOnCooldown((EntityPlayer) entityIn)) {
                if (((EntityPlayer) entityIn).getHealth() < ((EntityPlayer) entityIn).getMaxHealth()/2) {
                    ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 1));
                    ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 1));
                        startCooldown((EntityPlayer)entityIn);

                }

            }

        }
    }
}
