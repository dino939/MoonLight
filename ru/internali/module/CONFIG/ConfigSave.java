//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.CONFIG;

import ru.internali.module.*;
import ru.internali.utils.*;
import java.io.*;
import ru.internali.*;

public class ConfigSave extends Module
{
    public ConfigSave() {
        super("ConfigSave", "ConfigSave", Category.CONFIG);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final Config config = new Config();
        try {
            config.save();
        }
        catch (FileNotFoundException cause) {
            throw new RuntimeException(cause);
        }
        CatClient.instance.moduleManager.getModule("ConfigSave").setEnabled(false);
        this.setToggled(false);
    }
}
