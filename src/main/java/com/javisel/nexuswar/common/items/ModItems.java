package com.javisel.nexuswar.common.items;

import com.javisel.nexuswar.common.classes.gunslinger.DistanceBaton;
import com.javisel.nexuswar.common.classes.gunslinger.GunslingerClassStone;
import com.javisel.nexuswar.common.classes.warrior.*;
import net.minecraft.item.Item;
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
     public static ItemBase WarriorClassStone = new WarriorClassStone();
     //Gunslinger
    public static ItemBase GunslingerClassStone = new GunslingerClassStone();
    public static ItemBase DistanceBaton = new DistanceBaton();


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
                WarriorClassStone,
                //Gunslinger
                GunslingerClassStone,
                DistanceBaton
        );
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
         WarriorClassStone.registerItemModel();
         GunslingerClassStone.registerItemModel();
        DistanceBaton.registerItemModel();
    }

}