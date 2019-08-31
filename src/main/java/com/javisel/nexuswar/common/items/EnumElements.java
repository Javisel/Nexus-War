package com.javisel.nexuswar.common.items;


import net.minecraft.item.EnumDyeColor;

public enum EnumElements {
    DEFAULT(0,"default","default"),
    FIRE(1,"fire","fire"),
    WATER(2,"water","water"),
    EARTH(3,"earth","earth"),
    WIND(4,"wind","wind"),
    LIGHT(5,"light","light"),
    DARK(6,"dark","dark");

    private final int meta;
    private final String name;
    private final String unlocalizedName;
    private static final EnumElements[] ELEMENTLOOKUP = new EnumElements[values().length];

    EnumElements(int meta, String name, String unlocalizedName) {

        this.meta=meta;
        this.name=name;
        this.unlocalizedName=unlocalizedName;
    }

    public String getUnlocalizedName() {return unlocalizedName;}
    public static EnumElements byMetadata(int search)
    {
        if (search < 0 || search >= ELEMENTLOOKUP.length)
        {
            search = 0;
        }

        return ELEMENTLOOKUP[search];
    }
    public  int meta () {
        return  meta;
    }
    static
    {
        for (EnumElements EnumElements : values())
        {
            ELEMENTLOOKUP[EnumElements.meta()] = EnumElements;

        }
    }
}
