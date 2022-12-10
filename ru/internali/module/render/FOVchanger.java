//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class FOVchanger extends Module
{
    public void onDisable() {
        super.onDisable();
    }
    
    @SubscribeEvent
    public void FOVchanger(final EntityViewRenderEvent.FOVModifier fovModifier) {
        FOVchanger.mc.gameSettings.fovSetting = (float)CatClient.instance.settingsManager.getSettingByName(this, "FOV").getValDouble();
    }
    
    public FOVchanger() {
        super("FOVchanger", "FOVchanger", Category.RENDER);
        CatClient.instance.settingsManager.rSetting(new Setting("FOV", this, 100.0, 10.0, 150.0, false));
    }
}
