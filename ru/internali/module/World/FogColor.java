//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.World;

import ru.internali.module.*;
import ru.internali.*;
import java.util.*;
import ru.internali.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;

public class FogColor extends Module
{
    public static List Modes;
    private long spin;
    
    public FogColor() {
        super("FogColor", "FogColor", Category.WORLD);
        this.spin = 0L;
        FogColor.Modes.add("Spin");
        FogColor.Modes.add("Day");
        FogColor.Modes.add("Night");
        FogColor.Modes.add("Morning");
        FogColor.Modes.add("Sunset");
        CatClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Day", (ArrayList)FogColor.Modes));
        CatClient.instance.settingsManager.rSetting(new Setting("TimeSpin Speed", this, 2.0, 1.0, 10.0, false));
    }
    
    @SubscribeEvent
    public void onUpdate(final EntityViewRenderEvent.FogColors fogColors) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "TimeSpin Speed").getValDouble();
        if (valString.equalsIgnoreCase("Spin")) {
            FogColor.mc.world.setWorldTime(this.spin);
            this.spin += (long)(n * 100.0f);
        }
        else if (valString.equalsIgnoreCase("Day")) {
            FogColor.mc.world.setWorldTime(5000L);
        }
        else if (valString.equalsIgnoreCase("Night")) {
            FogColor.mc.world.setWorldTime(17000L);
        }
        else if (valString.equalsIgnoreCase("Morning")) {
            FogColor.mc.world.setWorldTime(0L);
        }
        else if (valString.equalsIgnoreCase("Sunset")) {
            FogColor.mc.world.setWorldTime(13000L);
        }
    }
    
    static {
        FogColor.Modes = new ArrayList();
    }
    
    @SubscribeEvent
    public void onFogColorRender(final EntityViewRenderEvent.FogColors fogColors) {
        final Color hsbColor = Color.getHSBColor((float)(Math.ceil((double)(System.currentTimeMillis() + 300L + 300L)) / 15.0 % 360.0 / 360.0), 0.4f, 1.0f);
        fogColors.setRed(hsbColor.getRed() / 255.0f);
        fogColors.setGreen(hsbColor.getGreen() / 255.0f);
        fogColors.setBlue(hsbColor.getBlue() / 255.0f);
    }
}
