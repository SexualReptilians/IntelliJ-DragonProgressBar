package sexualreptilians.dragonprogress;

import com.intellij.util.ui.JBUI;
import sexualreptilians.dragonprogress.configuration.DragonProgressState;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class ProgressBarUi extends BasicProgressBarUI {
    private final TintableImageIcon dragonIcon;
    private final Color progressColor;
    private final Color backgroundColor;
    private final boolean customBackColor;

    private boolean lastDirFlipped = false;
    private int lastX = 0;

    public ProgressBarUi() {
        this(DragonProgressState.getInstance());
    }

    // also used for previewing settings without modifying the state instance
    public ProgressBarUi(DragonProgressState settings) {
        // TODO: Handle nulls (for people messing with the config)
        dragonIcon = new TintableImageIcon(ImagePreloader.getImage(), settings.tintColor);
        progressColor = new Color(settings.progressColor);
        backgroundColor = new Color(settings.backColor);
        customBackColor = settings.useCustomBackColor;
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
        Graphics2D g2 = (Graphics2D)g;

        if (doBackground(g2)) {
            Insets b = this.progressBar.getInsets();
            int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
            int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);

            int amountFull = this.getAmountFull(b, barRectWidth, barRectHeight);

            // Paint the progress bar
            GradientPaint gradient = new GradientPaint(
                b.right,
                b.top,
                progressColor.darker(),
                b.right,
                b.top+(barRectHeight/2),
                progressColor,
                true
            );
            g2.setPaint(gradient);
            g2.fillRoundRect(b.left, b.top, b.left+amountFull, barRectHeight, barRectHeight/2, barRectHeight/2);

            // Clamp dragon so it never clips
            int dragonPosition = amountFull - dragonIcon.getIconWidth()/2;
            if (dragonPosition + dragonIcon.getIconWidth() > barRectWidth) {
                dragonPosition = barRectWidth - dragonIcon.getIconWidth();
            }

            // Paint the dragon
            dragonIcon.paintIcon(c, g2, b.left+dragonPosition, b.top);

            if (this.progressBar.isStringPainted()) {
                this.paintString(g2, b.left, b.top, barRectWidth, barRectHeight, barRectHeight, b);
            }
        }
    }

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) return;
        Graphics2D g2 = (Graphics2D)g;

        if (doBackground(g2)) {
            Insets b = this.progressBar.getInsets();
            int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
            int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);

            this.boxRect = this.getBox(this.boxRect);
            if (this.boxRect != null) {
                // Nexrem saw a dragon looking at him
                // and thought there's a third state of the dragon's direction

                // apparently the third state *does* exist... so we avoid that... maybe...
                // I believe you now Nexrem
                if (lastX != this.boxRect.x)
                    lastDirFlipped = (this.boxRect.x - lastX < 0);

                dragonIcon.paintIcon(c, g2, this.boxRect.x, b.top, lastDirFlipped);

                lastX = this.boxRect.x;
            }

            if (this.progressBar.isStringPainted()) {
                this.paintString(g2, b.left, b.top, barRectWidth, barRectHeight, barRectHeight, b);
            }
        }
    }

    // true if draw is possible
    private boolean doBackground(Graphics2D g2) {
        // Get bar bounding box
        Insets b = this.progressBar.getInsets();
        int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);

        // draw only if it's possible
        if (barRectWidth > 0 && barRectHeight > 0) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(this.progressBar.getForeground());

            // Paint the background
            g2.setPaint((customBackColor) ? backgroundColor : progressBar.getBackground().darker());
            g2.fillRoundRect(b.left, b.top, barRectWidth, barRectHeight, barRectHeight/2, barRectHeight/2);

            return true;
        }

        return false;
    }

    @Override
    protected int getBoxLength(int availableLength, int otherDimension) {
        return dragonIcon.getIconWidth();
    }
}
