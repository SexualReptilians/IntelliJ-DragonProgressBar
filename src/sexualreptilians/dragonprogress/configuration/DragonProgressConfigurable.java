package sexualreptilians.dragonprogress.configuration;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Provides controller functionality for application settings.
 */
public class DragonProgressConfigurable implements Configurable {

    private DragonProgressSettingsComponent settingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Dragon Progress Bar";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new DragonProgressSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        DragonProgressState settings = DragonProgressState.getInstance();
        boolean modified = !settingsComponent.getSelectedDragon().equals(settings.dragonImage)
                           |settingsComponent.getProgressColor() != settings.progressColor
                           |settingsComponent.getBackColor() != settings.backColor
                           |settingsComponent.getTintColor() != settings.tintColor
                           |settingsComponent.getCustomBackEnabled() != settings.useCustomBackColor;
        if (modified) {
            DragonProgressState s = new DragonProgressState();
            s.dragonImage = settingsComponent.getSelectedDragon();
            s.progressColor = settingsComponent.getProgressColor();
            s.tintColor = settingsComponent.getTintColor();
            s.backColor = settingsComponent.getBackColor();
            s.useCustomBackColor = settingsComponent.getCustomBackEnabled();
            settingsComponent.updateProgressBars(s);
        }
        return modified;
    }

    @Override
    public void apply() {
        DragonProgressState settings = DragonProgressState.getInstance();
        settings.dragonImage = settingsComponent.getSelectedDragon();
        settings.progressColor = settingsComponent.getProgressColor();
        settings.backColor = settingsComponent.getBackColor();
        settings.tintColor = settingsComponent.getTintColor();
        settings.useCustomBackColor = settingsComponent.getCustomBackEnabled();
        settingsComponent.updateProgressBars();
    }

    @Override
    public void reset() {
        DragonProgressState settings = DragonProgressState.getInstance();
        settingsComponent.setSelectedDragon(settings.dragonImage);
        settingsComponent.setProgressColor(settings.progressColor);
        settingsComponent.setBackColor(settings.backColor);
        settingsComponent.setTintColor(settings.tintColor);
        settingsComponent.setCheckboxCustomBack(settings.useCustomBackColor);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}