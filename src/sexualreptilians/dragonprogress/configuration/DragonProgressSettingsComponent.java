package sexualreptilians.dragonprogress.configuration;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.JBProgressBar;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;
import sexualreptilians.dragonprogress.ProgressBarUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class DragonProgressSettingsComponent {

    private final JPanel myMainPanel;

    private final ComboBox<DragonProgressColor> dragonList = new ComboBox<>();
    DefaultComboBoxModel<DragonProgressColor> listModel;

    private final JProgressBar previewDeterminate = new JProgressBar();
    private final JProgressBar previewIndeteminate = new JProgressBar();

    private final ColorPanel colorPicker = new ColorPanel();

    public DragonProgressSettingsComponent() {
        // TODO: Make this not shit
        listModel = (DefaultComboBoxModel<DragonProgressColor>) dragonList.getModel();
        listModel.addElement((new DragonProgressColor(0xFF0000, "/dragon_red.gif", "/dragon_red_m.gif", "Red")));
        listModel.addElement((new DragonProgressColor(0x00FF00, "/dragon_green.gif", "/dragon_green_m.gif", "Green")));
        listModel.addElement((new DragonProgressColor(0x0000FF, "/dragon_blue.gif", "/dragon_blue_m.gif", "Blue")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_black.gif", "/dragon_black_m.gif", "Black")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_white.gif", "/dragon_white_m.gif", "White")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_yellow.gif", "/dragon_yellow_m.gif", "Yellow")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_orange.gif", "/dragon_orange_m.gif", "Orange")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_lime.gif", "/dragon_lime_m.gif", "Lime")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_cyan.gif", "/dragon_cyan_m.gif", "Cyan")));
        listModel.addElement((new DragonProgressColor(0xff0000, "/dragon_purple.gif", "/dragon_purple_m.gif", "Purple")));
        dragonList.setSelectedIndex(0);
        dragonList.setModel(listModel);

        previewDeterminate.setValue(50);
        previewIndeteminate.setIndeterminate(true);

        myMainPanel = FormBuilder.createFormBuilder()
                .addComponent(previewDeterminate)
                .addComponent(previewIndeteminate)
                .addSeparator()
                .addLabeledComponent(new JBLabel("Select dragon:"), dragonList, 1)
                .addLabeledComponent(new JBLabel("Color:"), colorPicker, 1)
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
    public String getColorName() {
        return listModel.getElementAt(dragonList.getSelectedIndex()).getName();
    }

    @NotNull
    public String getDragonName() {
        return listModel.getElementAt(dragonList.getSelectedIndex()).getDragon();
    }

    @NotNull
    public String getDragonMName() {
        return listModel.getElementAt(dragonList.getSelectedIndex()).getDragonM();
    }

    @NotNull
    public int getColor() {
        return colorPicker.getSelectedColor().hashCode();
    }

    public void updateProgressBars() {
        previewDeterminate.setUI(new ProgressBarUi());
        previewIndeteminate.setUI(new ProgressBarUi());
    }

    public void setColor(int color) {
        colorPicker.setSelectedColor(new Color(color));
    }

    // TODO: This is disgusting
    public void setSelection(String name) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.getElementAt(i).getName().equals(name)) {
                dragonList.setSelectedIndex(i);
                return;
            }
        }
        dragonList.setSelectedIndex(0);
    }

}