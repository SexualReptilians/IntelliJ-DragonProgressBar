package sexualreptilians.dragonprogress;

import com.intellij.util.ui.UIUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import org.jdesktop.swingx.graphics.BlendComposite;

public class FilterableImageIcon extends ImageIcon {
    public final transient BufferedImage colored;
    private final Color tintColor;

    public FilterableImageIcon(Image resource, int tintColor) {
        super(resource);
        this.colored = UIUtil.createImage(null, this.getIconWidth(), this.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        this.tintColor = new Color(tintColor);
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        //if (!(g instanceof Graphics2D)) return;
        Graphics2D cg2d = colored.createGraphics();

        // Clear graphics and get the image
        cg2d.clearRect(0, 0, this.getIconWidth(), this.getIconHeight());

        cg2d.setComposite(AlphaComposite.SrcOver);
        cg2d.drawImage(this.getImage(), 0, 0, c);

        cg2d.setComposite(BlendComposite.Multiply);
        cg2d.setColor(tintColor);
        cg2d.fillRect(0, 0, this.getIconWidth(), this.getIconHeight());

        cg2d.setComposite(AlphaComposite.DstIn);
        cg2d.drawImage(this.getImage(), 0, 0, c);

        cg2d.dispose();

        if (this.getImageObserver() == null) {
            g.drawImage(colored, x, y, c);
        } else {
            g.drawImage(colored, x, y, this.getImageObserver());
        }
    }
}
