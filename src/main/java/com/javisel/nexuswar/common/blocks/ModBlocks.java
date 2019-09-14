package com.javisel.nexuswar.common.blocks;



import com.javisel.nexuswar.common.blocks.nexus.DarkNexus;
import com.javisel.nexuswar.common.blocks.nexus.LightNexus;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static final BlockBase LightNexus = new LightNexus();
    public static final BlockBase DarkNexus = new DarkNexus();
    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(

                DarkNexus,
                LightNexus
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(

                DarkNexus.createItemBlock(),
                LightNexus.createItemBlock()
        );
    }

    public static void registerModels() {

        DarkNexus.registerItemModel(Item.getItemFromBlock(DarkNexus));
        LightNexus.registerItemModel(Item.getItemFromBlock(LightNexus));

    }

    public static void RegisterOre() {


    }


}