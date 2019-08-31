package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.common.items.EnumElements;
import com.javisel.nexuswar.common.mobeffects.MobEffect;
import com.javisel.nexuswar.main.NexusWar;
import com.javisel.nexuswar.main.utilities.MobUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.swing.text.html.HTML;
import java.util.List;

public class HeroicShout extends AbilityItem {

    public HeroicShout() {
        super("warrior_ability_heroicshout",20*5*60);
        this.hasSubtypes=true;
    }

    @Override

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        NBTTagCompound nbt;


        if (!stack.hasTagCompound()) {
            nbt = new NBTTagCompound();
            nbt.setInteger("rank",1);
            nbt.setInteger("cooldown",60*20);
            stack.setTagCompound(nbt);
        }


        if (entityIn instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) entityIn;
            if (isSelected && !p.getCooldownTracker().hasCooldown(this)) {
                if (worldIn.isRemote) {
                    for (int x = -6; x < 6; x++) {
                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x, p.posY + 0.6D, p.posZ + 6, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x * (-1), p.posY + 0.6D, p.posZ + 6, 0.0D,
                                0.0D, 0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + 6, p.posY + 0.6D, p.posZ + x, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + 6, p.posY + 0.6D, p.posZ + x * (-1), 0.0D,
                                0.0D, 0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x, p.posY + 0.6D, p.posZ - 6, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX + x * (-1), p.posY + 0.6D, p.posZ - 6, 0.0D,
                                0.0D, 0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX - 6, p.posY + 0.6D, p.posZ + x, 0.0D, 0.0D,
                                0.0D);

                        worldIn.spawnParticle(EnumParticleTypes.CRIT, p.posX - 6, p.posY + 0.6D, p.posZ + x * (-1), 0.0D,
                                0.0D, 0.0D);

                    }
                }

            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        NBTTagCompound nbt =  playerIn.getHeldItem(handIn).getTagCompound();
        int damage = 8;
        if (nbt.getInteger("rank") ==2) damage = 14;
        playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 1));
        if (!worldIn.isRemote) {
            List<Entity> e = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(playerIn.posX + 5,
                    playerIn.posY + 5, playerIn.posZ + 5, playerIn.posX - 5, playerIn.posY - 5, playerIn.posZ - 5));

            for (int i = 0; i < e.size(); i++) {

                if (e.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase target = (EntityLivingBase) e.get(i);

                    if (target instanceof EntityPlayer) {
                        if (MobUtilities.isOnSameTeam(target, playerIn)) {
                            if (this.getMetadata(playerIn.getHeldItem(handIn)) ==0) {
                                target.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 1));

                            }
                            else if (this.getMetadata(playerIn.getHeldItem(handIn)) ==1) {
                                target.removePotionEffect(MobEffects.SLOWNESS);
                                target.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 400, 1));
                                target.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 0));

                            }
                        } else if (MobUtilities.canHurt(playerIn,target)) {
                            target.attackEntityFrom(DamageSource.causeIndirectDamage(playerIn,target), damage);

                        }

                    }

                    else {
                        target.attackEntityFrom(DamageSource.causeIndirectDamage(playerIn,target), 8.0F);
                        target.knockBack(playerIn, 2, 2, 2);
                    }
                }
            }
        }
        this.startCooldown(playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));

    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        EntityPlayer p = Minecraft.getMinecraft().player;
        int i = stack.getMetadata();
        tooltip.add("Class: Warrior");
        tooltip.add("ACTIVE");
        tooltip.add("Type: Area");
        tooltip.add("Effects: All");
        tooltip.add("Range: 5x5x5");

        tooltip.add(
                "Bellow a heroic shout, dealing 8 damage to enemies and knocking them back while empowering ally Heroes with Strength II for 15 seconds. ");
        if (i==1) {
            tooltip.add("Upgrade: Also Cleanses slows, grants Speed I and increases buff duration to 20s");

        }
        if (p != null) {
            if (p.getCooldownTracker().hasCooldown(this)) {
                double cd = this.getCooldown();
                double percentcd = (p.getCooldownTracker().getCooldown(this,
                        Minecraft.getMinecraft().getRenderPartialTicks()));

                percentcd = (p.getCooldownTracker().getCooldown(this, Minecraft.getMinecraft().getRenderPartialTicks())
                        * 100);
                cd /= 100;
                cd *= percentcd;
                cd /= 20;
                tooltip.add("Cooldown: " + (double) cd + "s");

            } else {
                tooltip.add("Cooldown: Ready");

            }

        }
    }

    @Override
    public String getTranslationKey(ItemStack stack){

        int i = stack.getMetadata();
        if (i ==1) {
            return super.getTranslationKey() + ".upgraded";

        }

        return super.getTranslationKey();
    }


    }