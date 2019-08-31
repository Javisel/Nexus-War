package com.javisel.nexuswar.main;

import com.javisel.nexuswar.common.classes.warrior.GrandstandResurrection;
import com.javisel.nexuswar.common.items.ModItems;
import com.javisel.nexuswar.common.mobeffects.Execution;
import com.javisel.nexuswar.common.mobeffects.MobEffect;
import com.javisel.nexuswar.common.mobeffects.ModMobEffects;
import com.javisel.nexuswar.main.utilities.ItemUtilities;
import com.javisel.nexuswar.main.utilities.MobUtilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent
    public void  RockSkin(LivingHurtEvent e) {

        if (e.getSource() != DamageSource.MAGIC)
        if (e.getEntityLiving() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) e.getEntityLiving();
            if (ItemUtilities.HasItem(player,ModItems.RockSkin)) {
                e.setAmount(e.getAmount() * 0.9F);

            }
        }

    }

    @SubscribeEvent
    public void Ressurrection(LivingDeathEvent e) {

        if (e.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) e.getEntityLiving();

            if (ItemUtilities.HasItem(p,ModItems.GrandStandResurrection) && !p.getCooldownTracker().hasCooldown(ModItems.GrandStandResurrection)) {

              ItemStack s = ItemUtilities.getItemstackfromitem(p,ModItems.GrandStandResurrection);
              GrandstandResurrection r = (GrandstandResurrection) s.getItem();
              r.startCooldown(p);
              p.heal(p.getMaxHealth());
              p.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
              if (s.getMetadata() ==1 ) {
                  p.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100, 2));
                  p.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 2));

              }
                e.setCanceled(true);
            }

        }


    }

    @SubscribeEvent
    public void  HeavyHit(LivingHurtEvent e) {

        if (e.getSource().getTrueSource() instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();

            if (player.getActivePotionEffect(ModMobEffects.HeavyHit) !=null) {

                e.setAmount(e.getAmount() * 1.5F);
                player.removePotionEffect(ModMobEffects.HeavyHit);
            }


        }

    }

    @SubscribeEvent
    public void DamageHandler(LivingAttackEvent e) {

        if (!MobUtilities.canHurt(e.getSource().getTrueSource(),e.getEntityLiving())) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void  Stunned(LivingEvent e) {

        if (e.isCancelable() && e.getEntityLiving().getActivePotionEffect(ModMobEffects.Stun) !=null) {

    if (e instanceof  LivingEntityUseItemEvent ){
        e.setCanceled(true);
    }
    if (e instanceof  LivingAttackEvent) {

        if (((LivingAttackEvent) e).getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase base = (EntityLivingBase) ((LivingAttackEvent) e).getSource().getTrueSource();

                e.setCanceled(true);

        }




    }
       if (e instanceof  LivingDestroyBlockEvent) {
           e.setCanceled(true);
            }

       if (e instanceof PlayerInteractEvent ) {
           e.setCanceled(true);
       }


        }

        }

    @SubscribeEvent
    public void Disarmed(LivingEntityUseItemEvent e) {

        if (e.getEntityLiving().getActivePotionEffect(ModMobEffects.Disarm) !=null) {
            e.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void DisarmedAttack(AttackEntityEvent e) {
        if (e.getEntityLiving().getActivePotionEffect(ModMobEffects.Disarm) !=null) {
            e.setCanceled(true);
        }
    }

}
