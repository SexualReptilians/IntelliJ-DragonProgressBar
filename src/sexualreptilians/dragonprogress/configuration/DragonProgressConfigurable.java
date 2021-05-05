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
        return "SDK: Application Settings Example";
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
        boolean modified = !settingsComponent.getColorName().equals(settings.name);
        return modified;
    }

    @Override
    public void apply() {
        DragonProgressState settings = DragonProgressState.getInstance();
        settings.name = settingsComponent.getColorName();
        settings.dragon = settingsComponent.getDragonName();
        settings.dragon_m = settingsComponent.getDragonMName();
        settings.color = settingsComponent.getColor();
    }

    @Override
    public void reset() {
        DragonProgressState settings = DragonProgressState.getInstance();
        settingsComponent.setSelection(settings.name);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

}