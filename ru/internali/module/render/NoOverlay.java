//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.client.*;
import ru.internali.module.*;

public class NoOverlay extends Module
{
    public static final NoOverlay INSTANCE;
    
    public void onDisable() {
        super.onDisable();
        GuiIngameForge.renderObjective = true;
    }
    
    public void onEnable() {
        super.onEnable();
        GuiIngameForge.renderObjective = false;
    }
    
    static {
        INSTANCE = new NoOverlay();
    }
    
    public NoOverlay() {
        super("NoOverlay", "TabOFF", Category.RENDER);
    }
}
