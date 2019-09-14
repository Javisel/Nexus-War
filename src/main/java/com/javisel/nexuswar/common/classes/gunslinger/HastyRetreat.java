package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.main.utilities.BlockUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HastyRetreat extends AbilityItem {
    public HastyRetreat() {
        super("gunslinger_ability_hastyretreat", 12000,1);
    }
    @Override

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.initializeItem(stack);

        if (stack.getMetadata()==1) {
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;

             if (player.getActivePotionEffect(MobEffects.SPEED)!=null) {
                 player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0));
             }

            }
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        playerIn.removePotionEffect(MobEffects.SPEED);
        playerIn.removePotionEffect(MobEffects.SLOWNESS);
     playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1));
     startCooldown(playerIn);

        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }




}
