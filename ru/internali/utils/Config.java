//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import ru.internali.module.*;
import ru.internali.*;
import java.util.*;
import ru.internali.settings.*;
import java.io.*;
import net.minecraft.client.*;

public class Config
{
    public File configs;
    public File dir;
    public File dataFile;
    
    public void save() throws FileNotFoundException {
        final ArrayList<String> list = new ArrayList<String>();
        for (final Module module : ModuleManager.getModules()) {
            list.add(String.valueOf(new StringBuilder().append("module:").append(module.getName()).append(":").append(module.isToggler()).append(":").append(module.getKey())));
        }
        final SettingsManager settingsManager = CatClient.instance.settingsManager;
        for (final Setting setting : CatClient.instance.settingsManager.getSettings()) {
            if (setting.isCheck()) {
                list.add(String.valueOf(new StringBuilder().append("Setting:").append(setting.getName()).append(":").append(setting.getParentMod().getName()).append(":").append(setting.getValBoolean())));
            }
            if (setting.isCombo()) {
                list.add(String.valueOf(new StringBuilder().append("Setting:").append(setting.getName()).append(":").append(setting.getParentMod().getName()).append(":").append(setting.getValString())));
            }
            if (setting.isSlider()) {
                list.add(String.valueOf(new StringBuilder().append("Setting:").append(setting.getName()).append(":").append(setting.getParentMod().getName()).append(":").append(setting.getValFloat())));
            }
        }
        final PrintWriter printWriter = new PrintWriter(this.dataFile);
        final Iterator<String> iterator3 = list.iterator();
        while (iterator3.hasNext()) {
            printWriter.println(iterator3.next());
        }
        printWriter.close();
    }
    
    public void load() throws IOException {
        final ArrayList<String> list = new ArrayList<String>();
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(this.dataFile));
        String e = bufferedReader.readLine();
        BufferedReader bufferedReader2;
        Exception ex;
        String[] split;
        Module module;
        Setting settingByName;
        Label_0089:Label_0103_Outer:
        while (true) {
            while (true) {
                while (e != null) {
                    list.add(e);
                    bufferedReader2 = bufferedReader;
                    try {
                        e = bufferedReader2.readLine();
                        continue Label_0103_Outer;
                    }
                    catch (Exception ex2) {
                        ex = ex2;
                        break Label_0089;
                    }
                    break;
                    ex.printStackTrace();
                    for (final String s : list) {
                        split = s.split(":");
                        if (s.toLowerCase().startsWith("module:")) {
                            module = CatClient.instance.moduleManager.getModule(split[1]);
                            if (module == null) {
                                continue Label_0103_Outer;
                            }
                            module.setEnabled(Boolean.parseBoolean(split[2]));
                            module.setKey(Integer.parseInt(split[3]));
                        }
                        else {
                            if (!s.toLowerCase().startsWith("setting:") || CatClient.instance.moduleManager.getModule(split[2]) == null || (settingByName = CatClient.instance.settingsManager.getSettingByName(split[1])) == null) {
                                continue Label_0103_Outer;
                            }
                            if (settingByName.isCheck()) {
                                settingByName.setValBoolean(Boolean.parseBoolean(split[3]));
                            }
                            if (settingByName.isCombo()) {
                                settingByName.setValString(split[3]);
                            }
                            if (!settingByName.isSlider()) {
                                continue Label_0103_Outer;
                            }
                            settingByName.setValDouble(Double.parseDouble(split[3]));
                            settingByName.setValFloat(Float.parseFloat(split[3]));
                        }
                    }
                    return;
                }
                try {
                    bufferedReader.close();
                    continue;
                }
                catch (Exception ex3) {
                    ex = ex3;
                }
                break;
            }
            continue Label_0089;
        }
    }
    
    public Config() {
        this.dir = new File(Minecraft.getMinecraft().gameDir, "config");
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        this.dataFile = new File(this.dir, "KeybindingsMod.json");
        if (!this.dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            this.load();
        }
        catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }
}
