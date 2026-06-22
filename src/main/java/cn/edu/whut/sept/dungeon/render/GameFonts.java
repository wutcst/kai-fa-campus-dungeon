package cn.edu.whut.sept.dungeon.render;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.UIManager;

final class GameFonts {
    private static final String FONT_FAMILY = chooseFontFamily();

    private GameFonts() {
    }

    static Font plain(int size) {
        return new Font(FONT_FAMILY, Font.PLAIN, size);
    }

    static Font bold(int size) {
        return new Font(FONT_FAMILY, Font.BOLD, size);
    }

    static void installDefaults() {
        Font plain = plain(14);
        UIManager.put("Label.font", plain);
        UIManager.put("Button.font", plain);
        UIManager.put("TextArea.font", plain);
        UIManager.put("ProgressBar.font", bold(12));
        UIManager.put("ScrollPane.font", plain);
        UIManager.put("Panel.font", plain);
    }

    private static String chooseFontFamily() {
        Set<String> available = new HashSet<String>(Arrays.asList(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        String[] candidates = {"Microsoft YaHei", "PingFang SC", "SimSun", Font.SANS_SERIF};
        for (String candidate : candidates) {
            if (available.contains(candidate)) {
                return candidate;
            }
        }
        return Font.SANS_SERIF;
    }
}
