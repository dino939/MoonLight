//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import ru.internali.module.*;
import net.minecraft.client.gui.*;
import ru.internali.*;
import java.util.*;

public class SelfDestruct extends Module
{
    public static boolean self;
    
    public SelfDestruct() {
        super("SelfDestruct", "clouse client", Category.OUTHER);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        SelfDestruct.mc.displayGuiScreen((GuiScreen)null);
        for (final Module module : CatClient.instance.moduleManager.getModuleList()) {
            if (Objects.equals(module.getName(), "SelfDestruct")) {
                continue;
            }
            module.setToggled(false);
        }
        SelfDestruct.self = true;
        SelfDestruct.mc.player.rotationYaw = 0.0f;
        SelfDestruct.mc.player.rotationPitch = 0.0f;
    }
    
    static {
        SelfDestruct.self = false;
    }
}
