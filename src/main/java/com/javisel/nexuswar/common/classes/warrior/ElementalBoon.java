package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.common.items.EnumElements;
import com.javisel.nexuswar.common.items.ItemBase;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.common.mobeffects.MobEffect;
import com.javisel.nexuswar.common.mobeffects.Stun;
import com.javisel.nexuswar.main.NexusWar;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import com.javisel.nexuswar.main.utilities.MobUtilities;
import com.javisel.nexuswar.main.utilities.RenderUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ElementalBoon extends AbilityItem {


    public ElementalBoon() {
        super("warrior_ability_elementalboon", 12000);
        this.setMaxDamage(201);
        this.hasSubtypes=true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {

        return (stack.hasTagCompound() && stack.getTagCompound().hasKey("isselecting") && stack.getTagCompound().getBoolean("isselecting"));
    }

    @Override
    public String getTranslationKey(ItemStack stack){

        int i = stack.getMetadata();
        if (i < 0  || i > EnumElements.values().length) {
            return super.getTranslationKey() + "." + EnumElements.byMetadata(0).getUnlocalizedName();

        }

        return super.getTranslationKey() + "." + EnumElements.byMetadata(i).getUnlocalizedName();
    }
    @Override
    public void registerItemModel() {


        for (int i =0; i <EnumElements.values().length; i++) {
            NexusWar.proxy.registerItemRenderer(this, i, name + "." + EnumElements.byMetadata(i).getUnlocalizedName());
        }

    }
    @Override
    public void InitializeItem(ItemStack stack) {
        super.InitializeItem(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 256;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {


        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("iselecting") && stack.getTagCompound().getBoolean("isselecting")) {
            return  stack.getTagCompound().getInteger("selectiontime");

        }

        return 0;


    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

            ItemStack oldBoon = playerIn.getHeldItem(handIn);

            NBTTagCompound nbt = oldBoon.getTagCompound();

           nbt.setBoolean("isselecting",true);

            ItemStack newBoon;
            int selection = oldBoon.getMetadata()+1;

            if (EnumElements.byMetadata(selection) == EnumElements.DEFAULT || EnumElements.values().length < selection) {
                selection = 1;
            }
            if (playerIn.isSneaking() && this.getMetadata(oldBoon) !=0) {
                nbt.setBoolean("isselecting",false);
                nbt.setInteger("selectiontime",0);
                this.startCooldown(playerIn);
            }
            else {

                newBoon = new ItemStack(ModItems.ElementalBoon, 1, selection);
                newBoon.setTagCompound(nbt);
                playerIn.setHeldItem(handIn, ItemStack.EMPTY);
                playerIn.setHeldItem(handIn, newBoon);
            }




        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));


    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        NBTTagCompound nbt;
        int i = stack.getMetadata();
        if (stack.hasTagCompound()) {
            nbt = stack.getTagCompound();

            if (!nbt.hasKey("selectiontime")) {
                nbt.setInteger("selectiontime", 0);
                nbt.setBoolean("isselecting", false);
            } else {
                if (nbt.getBoolean("isselecting")) {
                    nbt.setInteger("selectiontime", nbt.getInteger("selectiontime")+1);

                    if (nbt.getInteger("selectiontime") ==200) {
                        nbt.setBoolean("isselecting",false);
                        if (entityIn instanceof  EntityPlayer) {
                            this.startCooldown((EntityPlayer) entityIn);
                        }
                    }


                }
            }



        }
        else {
            nbt = new NBTTagCompound();

            if (!nbt.hasKey("selectiontime")) {
                nbt.setInteger("selectiontime", 200);
                nbt.setBoolean("isselecting", false);
            }
            stack.setTagCompound(nbt);
        }


        if (!worldIn.isRemote && entityIn instanceof EntityLivingBase && !nbt.getBoolean("isselecting")) {
            EntityLivingBase entity = (EntityLivingBase) entityIn;
            if (i == 1) {
                entity.extinguish();
                entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE));
            }
        else     if (i==2) {
                entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING));
            }
            else if (i==3) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SATURATION));

            }
            else if (i==4) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED));
            }
            else if (i==5) {
                entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION));

            }
            else if(i==6) {
                if (entity.getActivePotionEffect(MobEffects.POISON)!=null) {
                    entity.removePotionEffect(MobEffects.POISON);
                    if (!entity.getEntityWorld().isRemote) {
                        BlockPos pos = new BlockPos(entity.posX,entity.posY,entity.posZ);
                        RenderUtilities.spawnServerParticles(entityIn.getEntityWorld(),pos, EnumParticleTypes.CLOUD,555
                                );
                        worldIn.playSound(null,pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.AMBIENT,1.0F,1.0F);
                        entity.heal(1);

                    }
                }
                if (entity.getActivePotionEffect(MobEffects.WITHER)!=null) {
                    entity.removePotionEffect(MobEffects.WITHER);
                    if (!entity.getEntityWorld().isRemote) {
                        BlockPos pos = new BlockPos(entity.posX,entity.posY,entity.posZ);
                        RenderUtilities.spawnServerParticles(entityIn.getEntityWorld(),pos, EnumParticleTypes.CLOUD,555
                        );
                        worldIn.playSound(null,pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.AMBIENT,1.0F,1.0F);
                        entity.heal(1);
                    }
                }



            }
            else {

            }


        }

    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (int i = 0; i < EnumElements.values().length; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int i = stack.getMetadata();
         boolean isSelecting = false;

         if (stack.hasTagCompound() && stack.getTagCompound().hasKey("isselecting")) {
             isSelecting = stack.getTagCompound().getBoolean("isselecting");
         }
        tooltip.add("Class: Warrior");
        tooltip.add("ACTIVE");
        tooltip.add("Type: Buff");
        tooltip.add("Effects: Self");
        tooltip.add("Activate to gain 10s to cycle between 6 different elemental buffs to gain. After 10s the buff will lock in and this ability will go on cooldown for 10 minutes. Shift-Right click to lock in early.");


        if (i==1 || isSelecting) {
            tooltip.add("Fire: Fire Damage Immunity.");
        }
        if (i==2|| isSelecting){
            tooltip.add("Water:  Water Breathing.");
        }  if (i==3|| isSelecting) {
            tooltip.add("Earth:  Saturation.");
        }  if (i==4|| isSelecting) {
            tooltip.add("Wind: Speed I.");
        }  if (i==5|| isSelecting) {
            tooltip.add("Light: Night Vision.");
        }  if (i==6|| isSelecting) {
            tooltip.add("Dark: Poison & Wither Immunity.");
        }

    }


}
