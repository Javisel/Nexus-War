package com.javisel.nexuswar.common.items;

import com.javisel.nexuswar.common.classes.gunslinger.*;
import com.javisel.nexuswar.common.classes.warrior.*;
import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    //WARRIOR KIT
     public static ItemBase BattleStomp = new BattleStomp();
     public static ItemBase Cavalry = new Cavalry();
     public static ItemBase ElementalBoon = new ElementalBoon();
     public static ItemBase GrandStandResurrection = new GrandstandResurrection();

     public static ItemBase HeavyHit = new HeavyHit();
    public static ItemBase HeroicShout = new HeroicShout();
     public static ItemBase RockSkin = new RockSkin();
     public static ItemBase StunningStrike = new StunningStrike();
     public static ItemBase WarriorsMight = new WarriorsMight();
     public static ItemBase WarriorClassRune = new WarriorClassRune();
     //Gunslinger
    public static ItemArmor.ArmorMaterial MATERIAL_CAMO = EnumHelper.addArmorMaterial("gunslinger_camo", NexusWar.MODID + ":gunslinger/gunslinger_camo",-1,new int[]{1,2,2,1},0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0.0F);
    public static  ArmorBase GunslingerHelmet = new GunslingerArmorCamo("gunslinger_armor_camo_helmet",  1, EntityEquipmentSlot.HEAD);
    public static  ArmorBase GunslingerChestplate = new GunslingerArmorCamo("gunslinger_armor_camo_chestplate",1,EntityEquipmentSlot.CHEST );
    public static  ArmorBase GunslingerPants = new GunslingerArmorCamo("gunslinger_armor_camo_pants",2,EntityEquipmentSlot.LEGS );
    public static  ArmorBase GunslingerBoots = new GunslingerArmorCamo("gunslinger_armor_camo_boots",1,EntityEquipmentSlot.FEET);
    public static ItemBase GunslingerClassStone = new GunslingerClassRune();
    public static ItemBase DistanceBaton = new DistanceBaton();
    public static ItemBase FightThroughThePain = new FightThroughThePain();
    public static ItemBase HastyRetreat = new HastyRetreat();
    public static ItemBase IntimidationTactics = new IntimidationTactics();
    public static ItemBase TakeCover = new TakeCover();
    //GENEREAL ITEMS

    public  static void register(IForgeRegistry<Item> registry) {

        registry.registerAll(
                HeavyHit,

                BattleStomp,
        Cavalry,
    ElementalBoon,
        GrandStandResurrection,
          HeroicShout,
                RockSkin,
                StunningStrike,
                WarriorsMight,
                WarriorClassRune,
                //Gunslinger
                GunslingerClassStone,
                DistanceBaton,
                GunslingerHelmet,
                GunslingerChestplate,
                GunslingerPants,
                GunslingerBoots,
                FightThroughThePain,
                HastyRetreat,
                IntimidationTactics,
                TakeCover

        );
    }

    @SubscribeEvent
    public static void registerItemRenders(final ModelRegistryEvent ev) {
        registerModels();
    }

     public static void registerModels() {

         BattleStomp.registerItemModel();
                 Cavalry.registerItemModel();
             ElementalBoon.registerItemModel();
                 GrandStandResurrection.registerItemModel();

                 HeroicShout.registerItemModel();
                 RockSkin.registerItemModel();
                 StunningStrike.registerItemModel();
         WarriorsMight.registerItemModel();
         HeavyHit.registerItemModel();
         WarriorClassRune.registerItemModel();
         GunslingerClassStone.registerItemModel();
        DistanceBaton.registerItemModel();
        GunslingerHelmet.registerItemModel();
        GunslingerBoots.registerItemModel();
        GunslingerChestplate.registerItemModel();
        GunslingerPants.registerItemModel();
         FightThroughThePain.registerItemModel();
         HastyRetreat.registerItemModel();
         IntimidationTactics.registerItemModel();
            TakeCover.registerItemModel();
    }

}