//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package new_gui.setting;

import java.util.*;
import ru.internali.module.*;

public class SettingsManager
{
    private ArrayList settings;
    
    public ArrayList getSettings() {
        return this.settings;
    }
    
    public void rSetting(final Setting e) {
        this.settings.add(e);
    }
    
    public Setting getSettingByName(final String anotherString) {
        for (final Setting setting : this.getSettings()) {
            if (setting.getName().equalsIgnoreCase(anotherString)) {
                return setting;
            }
        }
        return null;
    }
    
    public SettingsManager() {
        this.settings = new ArrayList();
    }
    
    public ArrayList getSettingsByMod(final Module obj) {
        final ArrayList<Setting> list = new ArrayList<Setting>();
        for (final Setting e : this.getSettings()) {
            if (e.getParentMod().equals(obj)) {
                list.add(e);
            }
        }
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
}
