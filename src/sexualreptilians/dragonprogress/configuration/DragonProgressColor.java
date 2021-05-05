package sexualreptilians.dragonprogress.configuration;

import java.awt.*;

public class DragonProgressColor {

    private int color;
    private String dragon;
    private String dragon_m;
    private String name;

    public DragonProgressColor(int c, String d, String dm, String n) {
        color = c;
        dragon = d;
        dragon_m = dm;
        name = n;
    }

    public String getName() {
        return this.name;
    }

    public String getDragon() {
        return this.dragon;
    }

    public String getDragonM() {
        return this.dragon_m;
    }

    public int getColor() {
        return this.color;
    }

    public String toString()
    {
        return this.name;
    }
}
