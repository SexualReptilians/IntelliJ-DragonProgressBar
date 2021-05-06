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
                           |settingsComponent.getCustomBackEnabled() != settings.useCustomBackColor;
        return modified;
    }

    @Override
    public void apply() {
        DragonProgressState settings = DragonProgressState.getInstance();
        settings.dragonImage = settingsComponent.getSelectedDragon();
        settings.progressColor = settingsComponent.getProgressColor();
        settings.backColor = settingsComponent.getBackColor();
        settings.useCustomBackColor = settingsComponent.getCustomBackEnabled();
        settingsComponent.updateProgressBars();
    }

    @Override
    public void reset() {
        DragonProgressState settings = DragonProgressState.getInstance();
        settingsComponent.setSelectedDragon(settings.dragonImage);
        settingsComponent.setProgressColor(settings.progressColor);
        settingsComponent.setBackColor(settings.backColor);
        settingsComponent.setCheckboxCustomBack(settings.useCustomBackColor);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}