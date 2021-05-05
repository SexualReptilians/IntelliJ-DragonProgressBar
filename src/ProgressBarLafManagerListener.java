import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class ProgressBarLafManagerListener implements LafManagerListener {
    @Override
    public void lookAndFeelChanged(@NotNull LafManager lafManager) {
        updateProgressBarUI();
    }

    private static void updateProgressBarUI() {
        UIManager.put("ProgressBarUI", ProgressBarUi.class.getName());
        UIManager.getDefaults().put(ProgressBarUi.class.getName(), ProgressBarUi.class);
    }
}
