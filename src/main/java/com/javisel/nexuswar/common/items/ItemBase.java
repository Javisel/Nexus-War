package com.javisel.nexuswar.common.items;



import com.javisel.nexuswar.main.NexusWar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setRegistryName(NexusWar.MODID,name);
        setTranslationKey(name);

    }

    public void registerItemModel() {


        NexusWar.proxy.registerItemRenderer(this, 0, name);

    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


}