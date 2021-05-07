package sexualreptilians.dragonprogress;

import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class FilterableImageIcon extends ImageIcon {
    public final transient BufferedImage colored;
    private final Color tintColor;

    public FilterableImageIcon(URL resource, int tintColor) {
        super(resource);
        this.colored = UIUtil.createImage(null, this.getIconWidth(), this.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        this.tintColor = new Color((tintColor & 0x00_FFFFFF) | 0x80_000000, true);      // 0xAA_RRGGBB
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        //if (!(g instanceof Graphics2D)) return;
        Graphics2D cg2d = colored.createGraphics();

        // Clear graphics and get the image
        cg2d.setComposite(AlphaComposite.Clear);
        cg2d.fillRect(0, 0, this.getIconWidth(), this.getIconHeight());
        cg2d.setComposite(AlphaComposite.SrcOver);
        cg2d.drawImage(this.getImage(), 0, 0, c);

        // Tint the image
        cg2d.setComposite(AlphaComposite.SrcAtop);
        cg2d.setColor(tintColor);
        cg2d.fillRect(0, 0, this.getIconWidth(), this.getIconHeight());
        cg2d.dispose();

        if (this.getImageObserver() == null) {
            g.drawImage(colored, x, y, c);
        } else {
            g.drawImage(colored, x, y, this.getImageObserver());
        }
    }
}
