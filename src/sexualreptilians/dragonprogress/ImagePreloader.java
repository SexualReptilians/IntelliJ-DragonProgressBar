package sexualreptilians.dragonprogress;
import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class ImagePreloader implements AppLifecycleListener {
    private static Image dragonImage;

    @Override
    public void appStarting(@Nullable Project projectFromCommandLine) {
        dragonImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/dragon_white.gif"));
    }

    public static Image getImage() {
        return dragonImage;
    }
}
