//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.settings;

import ru.internali.module.*;
import java.util.*;

public class SettingsManager
{
    private ArrayList settings;
    
    public ArrayList getSettings() {
        return this.settings;
    }
    
    public Setting getSettingByName(final Module module, final String s) {
        for (final Setting setting : this.getSettings()) {
            if (setting.getName().equalsIgnoreCase(s) && setting.getParentMod() == module) {
                return setting;
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("[Tutorial] Error Setting NOT found: '").append(s).append("'!")));
        return null;
    }
    
    public ArrayList getSettings(final Module module) {
        final ArrayList<Setting> list = new ArrayList<Setting>();
        for (final Setting e : this.settings) {
            if (e.module == module) {
                list.add(e);
            }
        }
        return list;
    }
    
    public SettingsManager() {
        this.settings = new ArrayList();
    }
    
    public void add(final Setting e) {
        this.settings.add(e);
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
    
    public Setting getSettingById(final String anotherString) {
        for (final Setting setting : this.settings) {
            if (!setting.getId().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return setting;
        }
        return null;
    }
    
    public Setting getSettingByName(final String s) {
        for (final Setting setting : this.getSettings()) {
            if (setting.getName().equalsIgnoreCase(s)) {
                return setting;
            }
        }
        System.out.println(String.valueOf(new StringBuilder().append("Setting not found! (").append(s).append(").")));
        return null;
    }
    
    public void rSetting(final Setting e) {
        this.settings.add(e);
    }
}
