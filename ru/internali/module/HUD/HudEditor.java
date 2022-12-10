//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import ru.internali.module.*;
import ru.internali.*;
import net.minecraft.client.gui.*;

public class HudEditor extends Module
{
    public HudEditor() {
        super("HudEditor", "HudEditor", Category.HUD);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        HudEditor.mc.displayGuiScreen((GuiScreen)CatClient.hudEditor);
        this.setToggled(false);
    }
}
