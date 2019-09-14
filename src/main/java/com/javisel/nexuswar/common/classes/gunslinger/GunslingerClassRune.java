package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.classes.warrior.ClassRune;
import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GunslingerClassRune extends ClassRune {
    public GunslingerClassRune() {
        super("gunslinger_classrune");
    }

    @Override
    public void levelup(EntityPlayer player) {
        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
        nexusWarCapabilities.setLevel(nexusWarCapabilities.getLevel()+1);

        if (nexusWarCapabilities.getLevel()==3) {
            ItemUtilities.getNexusInventory(player).armorInventory.set(1, new ItemStack(ModItems.GunslingerHelmet));
            ItemUtilities.getNexusInventory(player).armorInventory.set(2, new ItemStack(ModItems.GunslingerChestplate));
            ItemUtilities.getNexusInventory(player).armorInventory.set(3, new ItemStack(ModItems.GunslingerPants));
            ItemUtilities.getNexusInventory(player).armorInventory.set(4, new ItemStack(ModItems.GunslingerBoots));


        }

    }

    @Override
    public void suiteMeUp(EntityPlayer player) {

        player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).setNexusClass(NexusClass.GUNSLINGER);


    }
}
