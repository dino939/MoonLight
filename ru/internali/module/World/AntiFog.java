//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.World;

import ru.internali.module.*;
import ru.internali.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiFog extends Module
{
    public AntiFog() {
        super("AntiFog", "AntiFog", Category.WORLD);
        CatClient.instance.settingsManager.rSetting(new Setting("Density", this, 1.0, 0.0, 100.0, true));
    }
    
    @SubscribeEvent
    public void fogDensity(final EntityViewRenderEvent.FogDensity fogDensity) {
        fogDensity.setDensity((float)(int)CatClient.instance.settingsManager.getSettingByName(this, "Density").getValDouble());
        fogDensity.setCanceled(true);
    }
}
