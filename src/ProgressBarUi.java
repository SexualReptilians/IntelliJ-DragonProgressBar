import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ProgressBarUi extends BasicProgressBarUI {

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new ProgressBarUi();
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(super.getPreferredSize(c).width, JBUI.scale(20));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        Rectangle r = new Rectangle(progressBar.getSize());
        g2d.setPaint(Color.red);
        g2d.fill(new RoundRectangle2D.Float(0f, 0f, r.width, r.height, 0f, 0f));
    }
}
