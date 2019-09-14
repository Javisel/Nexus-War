package com.javisel.nexuswar.common.classes.warrior;

import com.javisel.nexuswar.common.classes.AbilityItem;
import com.javisel.nexuswar.main.NexusWar;

public class ClassItem extends AbilityItem {
    public String getItemclass() {
        return itemclass;
    }

    String itemclass;
    public ClassItem(String name, String Class, int cooldown, int upgrades) {
        super(name,cooldown, upgrades);
        itemclass=Class;
    }
    @Override
    public void registerItemModel() {

        NexusWar.proxy.registerItemRenderer(this, 0, name);

    }

}
