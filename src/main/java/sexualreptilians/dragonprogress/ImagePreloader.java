package sexualreptilians.dragonprogress;

import com.intellij.ide.AppLifecycleListener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class ImagePreloader implements AppLifecycleListener {
    private static Image dragonImage;

    public static Image getImage() {
        return dragonImage;
    }

    @Override
    public void appFrameCreated(@NotNull List<String> commandLineArgs) {
        dragonImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/dragon_white.gif"));
    }
}
