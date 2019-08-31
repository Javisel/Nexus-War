package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.framework.NexusClass;
import com.javisel.nexuswar.common.classes.warrior.ClassStone;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GunslingerClassStone extends ClassStone {
    public GunslingerClassStone() {
        super("gunslinger_class_stone");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        suiteMeUp(playerIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void suiteMeUp(EntityPlayer player) {

        player.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY, null).setNexusClass(NexusClass.GUNSLINGER);

        player.inventory.clear();
        player.addItemStackToInventory(new ItemStack(ModItems.DistanceBaton));
        player.addItemStackToInventory(new ItemStack(ModItems.HeroicShout));
        player.addItemStackToInventory(new ItemStack(ModItems.StunningStrike));
        player.addItemStackToInventory(new ItemStack(ModItems.HeavyHit));
        player.addItemStackToInventory(new ItemStack(ModItems.WarriorsMight));
        player.addItemStackToInventory(new ItemStack(ModItems.GrandStandResurrection));
        player.addItemStackToInventory(new ItemStack(ModItems.RockSkin));
        player.addItemStackToInventory(new ItemStack(ModItems.BattleStomp));


    }
}
