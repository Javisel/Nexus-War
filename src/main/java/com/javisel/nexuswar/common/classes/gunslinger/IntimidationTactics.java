package com.javisel.nexuswar.common.classes.gunslinger;

import com.javisel.nexuswar.common.classes.AbilityItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class IntimidationTactics extends AbilityItem {

    public IntimidationTactics() {
        super("gunslinger_ability_intimidationtactics", 12000,1);
        // TODO Auto-generated constructor stub
    }



    @Override

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.initializeItem(stack);

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

        startCooldown(playerIn);
        if (!worldIn.isRemote) {
            List<Entity> e = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(playerIn.posX + 5,
                    playerIn.posY + 5, playerIn.posZ + 5, playerIn.posX - 5, playerIn.posY - 5, playerIn.posZ - 5));

            for (int i = 0; i < e.size(); i++) {

                if (e.get(i) instanceof EntityLivingBase) {
                    EntityLivingBase target = (EntityLivingBase) e.get(i);

                    if (target instanceof EntityPlayer) {
                        if (playerIn.getWorldScoreboard().getPlayersTeam(playerIn.getName()) != null && target
                                .isOnScoreboardTeam(playerIn.getWorldScoreboard().getPlayersTeam(playerIn.getName()))) {

                        } else if (!e.get(i)
                                .isOnScoreboardTeam(playerIn.getWorldScoreboard().getPlayersTeam(playerIn.getName()))) {
                            target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,200,3));

                        }

                    }

                    else if (target instanceof EntityTameable) {
                        EntityTameable pet = (EntityTameable) target;

                        if (pet.getOwnerId() == playerIn.getUniqueID()) {

                        }

                        else {
                            for (int g = 0; g < e.size(); g++) {

                                if (e.get(g) instanceof EntityPlayer) {
                                    if (pet.getOwner() == e.get(g)) {
                                        if (e.get(g).getTeam() == playerIn.getTeam()) {

                                        } else {
                                            target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,200,3));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,200,3));
                    }
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));

    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        EntityPlayer p = Minecraft.getMinecraft().player;
        tooltip.add("Class: Gunslinger");
        tooltip.add("ACTIVE");
        tooltip.add("Type: Area");
        tooltip.add("Effects: Enemies");
        tooltip.add("Range: 5x5x5");
        tooltip.add(
                "Let out an intimidating glare, infliciting Slowness IV on nearby enemies for 10s. ");

    }
    }
