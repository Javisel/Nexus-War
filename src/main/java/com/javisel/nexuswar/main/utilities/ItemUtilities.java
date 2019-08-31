package com.javisel.nexuswar.main.utilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class ItemUtilities {

    public static Boolean HasItem(EntityPlayer player, Item item) {

        Boolean returnvalue = false;
        InventoryPlayer I = player.inventory;

        for (int i = 0; i < I.getSizeInventory(); i++) {
            if (I.getStackInSlot(i).getItem().equals(item)) {
                returnvalue = true;

            }

        }

        return returnvalue;
    }

    public static void initializeTagCompound(ItemStack stack) {
        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt=stack.getTagCompound();
        }
        else {
            nbt = new NBTTagCompound();

        }


        stack.setTagCompound(nbt);


    }

    public static void addTagCompoundInteger(ItemStack stack, String key, int amount ){

        NBTTagCompound nbt;
        if (stack.hasTagCompound()) {
            nbt=stack.getTagCompound();
        }
        else {
            nbt = new NBTTagCompound();

        }

        nbt.setInteger(key,amount);

        stack.setTagCompound(nbt);

    }

    public static ItemStack getItemstackfromitem(EntityPlayer player, Item item) {

        Boolean returnvalue = false;
        InventoryPlayer I = player.inventory;

        for (int i = 0; i < I.getSizeInventory(); i++) {
            if (I.getStackInSlot(i).getItem().equals(item)) {
                return I.getStackInSlot(i);

            }

        }

        return null;
    }


}
