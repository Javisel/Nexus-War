package com.javisel.nexuswar.common.enchantments;

import com.javisel.nexuswar.common.classes.warrior.HeavyHit;
import com.javisel.nexuswar.common.items.ItemBase;
import electroblob.wizardry.item.ItemScroll;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModEnchantments {
    public static final EnumEnchantmentType SCROLLS = EnumHelper.addEnchantmentType("scrolls", (item)->(item instanceof ItemScroll));
    public static Enchantment ArcanaShield = new ArcanaShield();


    public static void register(IForgeRegistry<Enchantment> registry) {
            registry.register(ArcanaShield);
    }
}
