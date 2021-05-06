package sexualreptilians.dragonprogress.configuration;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import sexualreptilians.dragonprogress.ProgressBarUi;

import javax.swing.*;
import java.awt.Color;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class DragonProgressSettingsComponent {

    private final JPanel myMainPanel;

    private final ComboBox<DragonProgressColor> dragonList = new ComboBox<>();
    DefaultComboBoxModel<DragonProgressColor> listModel;

    private final JProgressBar previewDeterminate = new JProgressBar();
    private final JProgressBar previewIndeteminate = new JProgressBar();

    private final ColorPanel progressColorPicker = new ColorPanel();
    private final ColorPanel backColorPicker = new ColorPanel();

    private final JBCheckBox checkboxCustomBack = new JBCheckBox();

    public DragonProgressSettingsComponent() {
        // push the list to the combobox
        listModel = (DefaultComboBoxModel<DragonProgressColor>) dragonList.getModel();
        listModel.addAll(DragonProgressColor.dragons);
        dragonList.setSelectedIndex(0);

        // Setup preview progress bars
        previewDeterminate.setValue(50);
        previewIndeteminate.setIndeterminate(true);

        // create panel
        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(previewDeterminate)
                .addComponent(previewIndeteminate)
                .addSeparator(JBUI.scale(20))
                .addLabeledComponent(new JBLabel("Select dragon"), dragonList, 1)
                .addLabeledComponent(new JBLabel("Color"), progressColorPicker, 1)
                .addSeparator()
                .addLabeledComponent(new JBLabel("Use custom back color"), checkboxCustomBack, 1)
                .addLabeledComponent(new JBLabel("Background color"), backColorPicker, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return dragonList;
    }

    @NotNull
    public String getSelectedDragon() {
        return listModel.getElementAt(dragonList.getSelectedIndex()).getDragonImage();
    }

    public void setSelectedDragon(String image) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.getElementAt(i).getDragonImage().equals(image)) {
                dragonList.setSelectedIndex(i);
                return;
            }
        }
        dragonList.setSelectedIndex(-1);
    }

    public int getProgressColor() {
        return progressColorPicker.getSelectedColor().getRGB();
    }

    public void setProgressColor(int color) {
        progressColorPicker.setSelectedColor(new Color(color));
    }

    public int getBackColor() {
        return backColorPicker.getSelectedColor().getRGB();
    }

    public void setBackColor(int color) {
        backColorPicker.setSelectedColor(new Color(color));
    }

    public boolean getCustomBackEnabled() {
        return checkboxCustomBack.isSelected();
    }

    public void setCheckboxCustomBack(boolean val) {
        checkboxCustomBack.setSelected(val);
    }

    public void updateProgressBars() {
        previewDeterminate.setUI(new ProgressBarUi());
        previewIndeteminate.setUI(new ProgressBarUi());
    }
}