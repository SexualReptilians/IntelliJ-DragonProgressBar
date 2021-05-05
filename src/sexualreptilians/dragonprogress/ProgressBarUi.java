package sexualreptilians.dragonprogress;

import com.intellij.util.ui.JBUI;
import sexualreptilians.dragonprogress.configuration.DragonProgressState;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class ProgressBarUi extends BasicProgressBarUI {
    private ImageIcon dragonIcon;
    private ImageIcon dragonIconM;
    private Color progressColor;

    // Stupid state machine for mirroring
    private int lastX = 0;
    private enum Direction {
            FORWARD,
            BACK
    };
    private Direction dir = Direction.FORWARD;

    public ProgressBarUi() {
        DragonProgressState settings = DragonProgressState.getInstance();
        dragonIcon = new ImageIcon(this.getClass().getResource(settings.dragon));
        dragonIconM = new ImageIcon(this.getClass().getResource(settings.dragon_m));
        progressColor = new Color(settings.color);
    }

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new ProgressBarUi();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(dragonIcon.getIconHeight()));
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) return;

        // Get bar bounding box
        Insets b = this.progressBar.getInsets();
        int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);

        // draw only if it's possible
        if (barRectWidth > 0 && barRectHeight > 0) {
            int amountFull = this.getAmountFull(b, barRectWidth, barRectHeight);

            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(this.progressBar.getForeground());

            // Paint the background
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(b.left, b.top, barRectWidth, barRectHeight);

            // Clamp dragon so it never clips
            int dragonPosition = amountFull-dragonIcon.getIconWidth()/2;
            if (dragonPosition+dragonIcon.getIconWidth() > barRectWidth) {
                dragonPosition = barRectWidth-dragonIcon.getIconWidth();
            }
            // Paint the progress bar
            g2.setPaint(this.progressBar.getForeground().darker());
            g2.fillRect(b.left, b.top, b.left+amountFull, barRectHeight);
            // Paint the dragon
            dragonIcon.paintIcon(c, g2, b.left+dragonPosition, b.top);

            if (this.progressBar.isStringPainted()) {
                this.paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
            }
        }
    }

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        if (g instanceof Graphics2D) {
            Insets b = this.progressBar.getInsets();
            int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
            int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);
            if (barRectWidth > 0 && barRectHeight > 0) {
                Graphics2D g2 = (Graphics2D)g;

                g2.setColor(this.progressBar.getForeground());

                // Paint the background
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(b.left, b.top, barRectWidth, barRectHeight);
                //g2.fillRect(b.left, b.top, barRectWidth, barRectHeight);

                this.boxRect = this.getBox(this.boxRect);
                if (this.boxRect != null) {
                    // Paint the progress bar
                    //g2.setPaint(this.progressBar.getForeground().darker());
                    //g2.fillRect(this.boxRect.x, this.boxRect.y, this.boxRect.width, this.boxRect.height);

                    // Stupid state machine for mirroring
                    // Otherwise I'd have to reimplement the animator
                    if (this.boxRect.x-lastX < 0) {
                        dir = Direction.BACK;
                    }
                    else if (this.boxRect.x-lastX > 0) {
                        dir = Direction.FORWARD;
                    }
                    // Paint the dragon going either direction
                    switch (dir) {
                        case FORWARD:
                            dragonIcon.paintIcon(c, g2, this.boxRect.x, b.top);
                            break;
                        case BACK:
                            dragonIconM.paintIcon(c, g2, this.boxRect.x, b.top);
                            break;
                    }

                    lastX = this.boxRect.x;
                }

                if (this.progressBar.isStringPainted()) {
                    this.paintString(g, b.left, b.top, barRectWidth, barRectHeight, barRectWidth, b);
                }

            }
        }
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return dragonIcon.getIconWidth();
    }
}