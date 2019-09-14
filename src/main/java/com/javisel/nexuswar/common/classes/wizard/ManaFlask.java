package com.javisel.nexuswar.common.classes.wizard;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.common.mobeffects.ModMobEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ManaFlask  extends AbilityItem {


    public ManaFlask() {
        super("wizard_ability_manaflask", 20*10*60, 0);
        this.setMaxStackSize(3);
        this.setMaxDamage(60*60*20*2);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
       if (entityLiving instanceof  EntityPlayer) {
           EntityPlayer player = (EntityPlayer) entityLiving;
           player.addPotionEffect(new PotionEffect(ModMobEffects.Restoration, 100, 0));

           if (stack.getCount() > 1){ stack.shrink(1);

               stack.setItemDamage((20*10*60) - 1);
           }
           else {
               startCooldown(player);
           }

       }



        return stack;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);


        if (stack.getCount()<3) {

            stack.setItemDamage(stack.getItemDamage()-1);

            if (stack.getItemDamage() == 0) {
                stack.setCount(stack.getCount()+1);
                stack.setItemDamage(20*60*10);
            }
        }


    }
}
