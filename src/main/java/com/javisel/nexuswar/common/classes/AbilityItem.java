package com.javisel.nexuswar.common.classes;

import com.javisel.nexuswar.common.items.ItemBase;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class AbilityItem extends ItemBase {


    int cooldown;
    public AbilityItem(String name, int cooldown) {
        super(name);
        this.setMaxStackSize(1);
        this.cooldown=cooldown;
    }

    public void InitializeItem(ItemStack stack) {
        NBTTagCompound nbt;
        if(stack.hasTagCompound()) {
            nbt=stack.getTagCompound();
        }
        else {
            nbt = new NBTTagCompound();
        }
        nbt.setInteger("cooldown",getCooldown());
            stack.setTagCompound(nbt);
    }


    public int getCooldown() {return  cooldown;}

    public boolean startCooldown(EntityPlayer player) {

        player.getCooldownTracker().setCooldown(this,cooldown);

        return true;
    }

    public boolean isOnCooldown(EntityPlayer player) {

        return  player.getCooldownTracker().hasCooldown(this);
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        NBTTagCompound nbt;

        if (!stack.hasTagCompound()) {

            nbt = new NBTTagCompound();
            nbt.setInteger("rank",1);
            nbt.setInteger("cooldown",0);
            stack.setTagCompound(nbt);
        }
    }

    public void activateActiveComponent(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

    }

}
