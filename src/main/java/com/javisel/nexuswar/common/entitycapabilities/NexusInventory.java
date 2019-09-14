package com.javisel.nexuswar.common.entitycapabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class NexusInventory extends InventoryPlayer {


    public NexusInventory(EntityPlayer playerIn) {
        super(playerIn);
    }
}
