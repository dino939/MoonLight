//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils.HudEditor;

import net.minecraft.client.gui.*;
import java.util.*;
import java.io.*;
import ru.internali.*;
import ru.internali.module.*;

public class HudEditor extends GuiScreen
{
    ArrayList hudComponents;
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        for (final HudComponent hudComponent : this.hudComponents) {
            if (hudComponent.module.isEnable()) {
                hudComponent.mouseClicked(n, n2, n3);
            }
        }
    }
    
    public HudEditor() {
        this.hudComponents = new ArrayList();
        final ModuleManager moduleManager = CatClient.instance.moduleManager;
        for (final Module module : ModuleManager.modules) {
            if (module.isHud()) {
                this.hudComponents.add(new HudComponent(module));
            }
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        for (final HudComponent hudComponent : this.hudComponents) {
            if (hudComponent.module.isEnable()) {
                hudComponent.drawScreen(n, n2, n3);
            }
        }
    }
}
