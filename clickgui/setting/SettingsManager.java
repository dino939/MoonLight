//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package clickgui.setting;

import ru.internali.module.*;
import java.util.*;

public class SettingsManager
{
    private ArrayList settings;
    
    public SettingsManager() {
        this.settings = new ArrayList();
    }
    
    public ArrayList getSettings() {
        return this.settings;
    }
    
    public void rSetting(final Setting e) {
        this.settings.add(e);
    }
    
    public ArrayList getSettingsByMod(final Module module) {
        final ArrayList list = new ArrayList();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
    
    public Setting getSettingByName(final String s) {
        for (final Setting setting : this.getSettings()) {
            if (setting.getName().equalsIgnoreCase(s)) {
                return setting;
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("Error Setting NOT found: '").append(s).append("'!")));
        return null;
    }
}
