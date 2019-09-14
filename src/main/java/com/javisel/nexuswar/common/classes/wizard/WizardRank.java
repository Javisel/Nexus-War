package com.javisel.nexuswar.common.classes.wizard;

public enum WizardRank {

    NOVICE(0,"Novice"),
    APPRENTICE(1,"Apprentice"),
    ADVANCED(2,"Advanced"),
    MASTER(3,"Master");


    private final int meta;
    private final String name;
    private static final WizardRank[] ELEMENTLOOKUP = new WizardRank[values().length];

    WizardRank(int meta, String name) {

        this.meta=meta;
        this.name=name;
        ;
    }

    public String getName() {return name;}
    public static WizardRank byMetadata(int search)
    {
        if (search < 0 || search >= ELEMENTLOOKUP.length)
        {
            search = 0;
        }

        return ELEMENTLOOKUP[search];
    }
    public  final int meta() {
        return  meta;
    }
    static
    {
        for (WizardRank WizardRank : values())
        {
            ELEMENTLOOKUP[WizardRank.meta()] = WizardRank;

        }
    }
}
