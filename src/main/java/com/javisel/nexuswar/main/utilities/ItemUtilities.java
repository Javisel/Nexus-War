package com.javisel.nexuswar.main.utilities;

import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusInventory;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
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

    public static void SwapInventories(EntityPlayer player) {
        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);

        if (!nexusWarCapabilities.isUsingNexusInventory()) {
            nexusWarCapabilities.setIsUsingNexusInventory(true);
        }
        else {
            nexusWarCapabilities.setIsUsingNexusInventory(false);

        }
            for (int i = 0; i < player.inventory.armorInventory.size(); i++) {
                ItemStack thestack = nexusWarCapabilities.nexusInventory().armorInventory.get(i);
                nexusWarCapabilities.nexusInventory().armorInventory.set(i, player.inventory.armorInventory.get(i));
                player.inventory.armorInventory.set(i,thestack);



            }
            for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
                ItemStack thestack = nexusWarCapabilities.nexusInventory().mainInventory.get(i);
                nexusWarCapabilities.nexusInventory().mainInventory.set(i, player.inventory.mainInventory.get(i));
                player.inventory.mainInventory.set(i,thestack);

            }
            for (int i = 0; i < player.inventory.offHandInventory.size(); i++) {
                ItemStack thestack = nexusWarCapabilities.nexusInventory().offHandInventory.get(i);
                nexusWarCapabilities.nexusInventory().offHandInventory.set(i, player.inventory.offHandInventory.get(i));
                player.inventory.offHandInventory.set(i,thestack);

            }






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

    public static InventoryPlayer getNexusInventory(EntityPlayer player){
        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
        if        (nexusWarCapabilities.isUsingNexusInventory()) {

            return (player.inventory);

        }
        else {
            return  nexusWarCapabilities.nexusInventory();
        }



    }



}
