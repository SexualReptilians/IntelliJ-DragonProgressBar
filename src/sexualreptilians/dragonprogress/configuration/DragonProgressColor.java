package sexualreptilians.dragonprogress.configuration;

import java.util.ArrayList;

public class DragonProgressColor {

    public static final ArrayList<DragonProgressColor> dragons = new ArrayList<>() {
        {
            add(new DragonProgressColor("dragon_red", "Red"));
            add(new DragonProgressColor("dragon_green", "Green"));
            add(new DragonProgressColor("dragon_blue", "Blue"));
            add(new DragonProgressColor("dragon_black", "Black"));
            add(new DragonProgressColor("dragon_white", "White"));
            add(new DragonProgressColor("dragon_yellow", "Yellow"));
            add(new DragonProgressColor("dragon_orange", "Orange"));
            add(new DragonProgressColor("dragon_lime", "Lime"));
            add(new DragonProgressColor("dragon_cyan", "Cyan"));
            add(new DragonProgressColor("dragon_purple", "Purple ^.=.^"));
        }
    };

    private final String image;
    private final String name;

    public DragonProgressColor(String img, String nm) {
        image = img;
        name = nm;
    }

    public String getName() {
        return this.name;
    }

    public String getDragonImage() {
        return this.image;
    }

    public String toString()
    {
        return getName();
    }
}
