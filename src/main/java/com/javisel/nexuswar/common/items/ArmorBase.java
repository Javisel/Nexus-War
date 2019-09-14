package com.javisel.nexuswar.common.items;

import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ArmorBase extends ItemArmor  {

String name;
    public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setTranslationKey(name);
        setRegistryName(name);
        this.name=name;
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
            nbt.setBoolean("cantdrop", true);
            nbt.setBoolean("initalized",true);
            stack.setTagCompound(nbt);

        }
    }
    public void registerItemModel() {


        NexusWar.proxy.registerItemRenderer(this, 0, name);

    }



}
