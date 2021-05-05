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
    // TODO: Surely this can be stored better and not so ugly
    public int color = 0xFFFFFF;
    public String dragon = "/dragon_white.gif";
    public String dragon_m = "/dragon_white_m.gif";
    public String name = "White";

    public static DragonProgressState getInstance() {
        return ServiceManager.getService(DragonProgressState.class);
    }

    public DragonProgressState getState() {
        return this;
    }

    @NotNull
    public void loadState(DragonProgressState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}