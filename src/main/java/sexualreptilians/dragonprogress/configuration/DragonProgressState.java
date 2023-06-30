package sexualreptilians.dragonprogress.configuration;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@State(
        name = "DragonProgress",
        storages = {@Storage("DragonProgress.xml")}
)
public class DragonProgressState implements PersistentStateComponent<DragonProgressState> {
    public int progressColor = 0xFFFFFF;
    public int backColor = 0x000000;
    public int tintColor = 0xFFFFFF;
    public boolean useCustomBackColor = false;

    public static DragonProgressState getInstance() {
        return ServiceManager.getService(DragonProgressState.class);
    }

    @Override
    public DragonProgressState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull DragonProgressState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}