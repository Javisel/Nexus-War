package com.javisel.nexuswar.common.classes;

import com.javisel.nexuswar.common.items.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class AbilityItem extends ItemBase {


    private int upgrades;
    private int cooldown;
    public AbilityItem(String name, int cooldown, int upgrades) {
        super(name);
        this.setMaxStackSize(1);
        this.cooldown=cooldown;
        this.upgrades=upgrades;
        if (upgrades>0) this.hasSubtypes=true;
    }

    public int getUpgrades() {
        return upgrades;
    }

    @Override
    public String getTranslationKey(ItemStack stack) {

        int i = stack.getMetadata();
        if (i>0 && upgrades>0) {
            if (i > upgrades) {i = upgrades;}
            return this.getTranslationKey()+".upgrade_" + i;
        }
        else {
            return this.getTranslationKey();
        }
    }



    public void initializeItem(ItemStack stack) {
        NBTTagCompound nbt;
        if(stack.hasTagCompound()) {
            nbt=stack.getTagCompound();
        }
        else {
            nbt = new NBTTagCompound();
        }
        if (!nbt.hasKey("initalized")) {
            nbt.setInteger("cooldown", getCooldown());
            nbt.setBoolean("cantdrop", true);
            nbt.setBoolean("initalized",true);
            stack.setTagCompound(nbt);

        }
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

        initializeItem(stack);
    }

    public void activateActiveComponent(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

    }


}
