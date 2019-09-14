package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.common.entitycapabilities.INexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilities;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.common.items.ItemBase;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ClassRune extends AbilityItem {
    public ClassRune(String name) {
        super(name,0,0);
        this.setMaxStackSize(20);
    }

    public void suiteMeUp(EntityPlayer player) {

    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return super.getDurabilityForDisplay(stack);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        NBTTagCompound nbt = stack.getTagCompound();

        if (entityIn instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) entityIn;
            INexusWarCapabilities nexusWarCapabilities = entityPlayer.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null);
            if (!nbt.hasKey("suited")) {
                nbt.setBoolean("suited", false);
                nbt.setDouble("xp", nexusWarCapabilities.getEXP());
                //TODO EXP THRESHOLD
                nbt.setDouble("levelupthreshold",100);

            } else {

                nbt.setDouble("xp", nexusWarCapabilities.getEXP());
                if (nbt.getDouble("xp") >= nbt.getDouble("levelupthreshold")) {
                    levelup(entityPlayer);
                    stack.setCount(stack.getCount()+1);
                    nexusWarCapabilities.setEXP(0);
                }
            }

        }
    }

    public void levelup(EntityPlayer player) {

    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).getNexusClass() ==null) {


            stack.getTagCompound().setBoolean("suited",true);
            suiteMeUp(playerIn);
        } else {
             stack = playerIn.getHeldItem(handIn);

            ItemUtilities.SwapInventories(playerIn);

            playerIn.setHeldItem(handIn,stack);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if (!stack.getTagCompound().hasKey("activated")) {

            tooltip.add("SINGLE USE: Gain the powers of this class.");
        } else {
            tooltip.add("ACTIVE: Initiate magical girl/boy transformation sequence and assume your Hero Form. Or, detransform. ");

        }
        tooltip.add("EVOLUTION: This item tracks your progress towards your next level. ");



    }
}
