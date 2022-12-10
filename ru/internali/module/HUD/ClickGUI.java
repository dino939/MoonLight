//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import ru.internali.module.*;
import java.util.*;
import ru.internali.*;
import net.minecraft.client.gui.*;

public class ClickGUI extends Module
{
    public static List Modes;
    
    public ClickGUI() {
        super("ClickGUI", "menu.skeet", Category.HUD);
        this.setKey(54);
    }
    
    static {
        ClickGUI.Modes = new ArrayList();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        ClickGUI.mc.displayGuiScreen((GuiScreen)CatClient.guib);
        this.setToggled(false);
    }
}
