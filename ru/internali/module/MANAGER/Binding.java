//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.MANAGER;

import net.minecraft.client.renderer.*;
import ru.internali.*;
import net.minecraft.client.gui.*;
import ru.internali.module.*;

public class Binding extends Module
{
    private static RenderItem itemRender;
    
    @Override
    public void onEnable() {
        super.onEnable();
        Binding.mc.displayGuiScreen((GuiScreen)CatClient.instance.csgui);
        this.setToggled(false);
    }
    
    public Binding() {
        super("Binding", "ArmorHUD", Category.MANAGER);
    }
}
