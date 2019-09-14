package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.items.ArmorBase;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GunslingerArmorCamo extends ArmorBase {
    public GunslingerArmorCamo(String name, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(name, ModItems.MATERIAL_CAMO, renderIndexIn, equipmentSlotIn);
    }



}
