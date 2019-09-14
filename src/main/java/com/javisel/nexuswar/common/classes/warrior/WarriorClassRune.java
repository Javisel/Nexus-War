package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class WarriorClassRune extends ClassRune {
    public WarriorClassRune() {
        super("warrior_classrune");
    }

    @Override
    public void levelup(EntityPlayer player) {
        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);

        nexusWarCapabilities.setLevel(nexusWarCapabilities.getLevel()+1);
        if (nexusWarCapabilities.getLevel() == 2){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.WarriorsMight,1));
        }
        if (nexusWarCapabilities.getLevel() == 6){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.HeroicShout,1));
        }
        if (nexusWarCapabilities.getLevel() == 7){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.ElementalBoon,1));
        }
        if (nexusWarCapabilities.getLevel() == 8){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.Cavalry,1));
        }
        if (nexusWarCapabilities.getLevel() == 9){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.RockSkin,1));
        }
        if (nexusWarCapabilities.getLevel() == 11){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.HeavyHit,1));
        }
        if (nexusWarCapabilities.getLevel() == 13){
            ItemUtilities.getNexusInventory(player).clearMatchingItems(ModItems.Cavalry,0,1,null);
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.Cavalry,1,1));

        }
        if (nexusWarCapabilities.getLevel() == 15){
            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.GrandStandResurrection,1));

        }
        if (nexusWarCapabilities.getLevel() == 16){
            ItemUtilities.getNexusInventory(player).clearMatchingItems(ModItems.HeroicShout,0,1,null);

            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.HeroicShout,1,1));

        }
        if (nexusWarCapabilities.getLevel() == 17){

            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.BattleStomp,1,0));

        }
        if (nexusWarCapabilities.getLevel() == 18){

            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.StunningStrike,1,0));

        }
        if (nexusWarCapabilities.getLevel() == 20){

            ItemUtilities.getNexusInventory(player).addItemStackToInventory(new ItemStack(ModItems.GrandStandResurrection,1,1));

        }
    }

    @Override
    public void suiteMeUp(EntityPlayer player) {
        player.inventory.clear();
        INexusWarCapabilities nexusWarCapabilities = player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
        nexusWarCapabilities.setNexusClass(NexusClass.WARRIOR);
        nexusWarCapabilities.setNexusInventory(player);



        ItemUtilities.SwapInventories(player);

}
}
