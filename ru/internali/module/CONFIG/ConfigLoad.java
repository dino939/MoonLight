//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.CONFIG;

import ru.internali.module.*;
import ru.internali.utils.*;
import java.io.*;
import ru.internali.*;

public class ConfigLoad extends Module
{
    public ConfigLoad() {
        super("ConfigLoad", "ConfigLoad", Category.CONFIG);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final Config config = new Config();
        try {
            config.load();
        }
        catch (IOException cause) {
            throw new RuntimeException(cause);
        }
        CatClient.instance.moduleManager.getModule("ConfigLoad").setEnable(false);
        this.setToggled(false);
    }
}
