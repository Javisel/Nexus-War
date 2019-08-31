package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.common.entitycapabilities.NexusWarCapabilitiesProvider;
import com.javisel.nexuswar.main.utilities.MobUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class Cavalry extends AbilityItem {
    public Cavalry() {
        super("warrior_ability_cavalry",60*60*24*20);
        this.hasSubtypes=true;
    }


    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        NBTTagCompound nbt;

        if (!stack.hasTagCompound()) {

            nbt = new NBTTagCompound();

        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {


                    EntityHorse horsy = new EntityHorse(worldIn);
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(horsy);
                        MobUtilities.spawnCreature(worldIn,horsy,playerIn.posX,playerIn.posY,playerIn.posZ);
                    }
                    ItemStack horseArmour;
                    ItemStack thisitem = playerIn.getHeldItem(handIn);

                    horsy.setTamedBy(playerIn);
                    horsy.setHorseSaddled(true);
                    horsy.setPositionAndUpdate(playerIn.posX,playerIn.posY,playerIn.posZ);
                    horsy.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).setTeam(playerIn.getCapability(NexusWarCapabilitiesProvider.I_NEXUS_WAR_CAPABILITY,null).getTeam());


               // this.startCooldown(playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));


    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        EntityPlayer p = Minecraft.getMinecraft().player;
        int i = stack.getMetadata();
        tooltip.add("Class: Warrior");
        tooltip.add("ACTIVE");
        tooltip.add("Type: SUMMON");

        tooltip.add(
                "Summon a tamed Horse Preequiped with Golden Horse Armour and a Saddle.");
        if (i==1) {
            tooltip.add("Upgrade: Horse has Resistance II, Diamond Armour and Speed I.");
        }

    }


}
