package com.javisel.nexuswar.common.enchantments;

import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.enchantment.Enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArcanaShield extends Enchantment {
    protected ArcanaShield() {
        super(Rarity.VERY_RARE, ModEnchantments.SCROLLS, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
            this.setRegistryName(NexusWar.MODID,"arcana_shield");
            this.setName("Arcana Shield");

    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }


    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
