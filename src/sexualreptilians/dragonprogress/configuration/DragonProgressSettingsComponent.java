package sexualreptilians.dragonprogress.configuration;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog.
 */
public class DragonProgressSettingsComponent {

    private final JPanel myMainPanel;
    private final JBList<DragonProgressColor> colorsList = new JBList<>();
    private final DefaultListModel<DragonProgressColor> listModel = new DefaultListModel<>();

    public DragonProgressSettingsComponent() {
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

        colorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorsList.setVisibleRowCount(-1);
        colorsList.setSelectedIndex(0);
        colorsList.setModel(listModel);

        myMainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Select Color:"), colorsList, 1, true)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return myMainPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return colorsList;
    }

    @NotNull
    public String getColorName() {
        return colorsList.getSelectedValue().getName();
    }

    @NotNull
    public String getDragonName() {
        return colorsList.getSelectedValue().getDragon();
    }

    @NotNull
    public String getDragonMName() {
        return colorsList.getSelectedValue().getDragonM();
    }

    @NotNull
    public int getColor() {
        return colorsList.getSelectedValue().getColor();
    }

    public void setSelection(String name) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.get(i).getName().equals(name)) {
                colorsList.setSelectedIndex(i);
                return;
            }
        }
        colorsList.setSelectedIndex(0);
    }

}