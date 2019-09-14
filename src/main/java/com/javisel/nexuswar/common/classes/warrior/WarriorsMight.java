package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class WarriorsMight extends AbilityItem {

    public WarriorsMight() {
        super("warrior_ability_warriorsmight",20*5*10,1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

            if (!worldIn.isRemote) {

                playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1200, 1));
                playerIn.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 2));
                this.startCooldown(playerIn);


            }


        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        NBTTagCompound nbt;

        if (!stack.hasTagCompound()) {

            nbt = new NBTTagCompound();
            nbt.setInteger("rank",1);
            nbt.setInteger("cooldown",60*5*20);
            stack.setTagCompound(nbt);
        }



        if (!worldIn.isRemote) {
            if (entityIn instanceof EntityLivingBase) {
      ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE));

            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        EntityPlayer p = Minecraft.getMinecraft().player;
        int i = stack.getMetadata();
        tooltip.add("Class: Warrior");
        tooltip.add("PASSIVE & ACTIVE");
        tooltip.add("Type: Buff");
        tooltip.add("Effects: Self");
        tooltip.add("Range: 5x5x5");
        tooltip.add("PASSIVE: +Resistance I");
        tooltip.add("ACTIVE: +Strength II & Absorption III for 1 minute.");

        if (i==1) {
            tooltip.add("Upgrade: Also Cleanses slows, grants Speed I and increases buff duration to 20s");
        }

    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {

        return super.getTranslationKey();
    }
}
