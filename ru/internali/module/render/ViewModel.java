//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.render;

import net.minecraftforge.client.event.*;
import ru.internali.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class ViewModel extends Module
{
    public static List listA;
    
    public void onDisable() {
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onRender(final RenderSpecificHandEvent renderSpecificHandEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "rightPosX").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "rightPosY").getValDouble();
        final float n3 = (float)CatClient.instance.settingsManager.getSettingByName(this, "rightPosZ").getValDouble();
        final float n4 = (float)CatClient.instance.settingsManager.getSettingByName(this, "leftPosX").getValDouble();
        final float n5 = (float)CatClient.instance.settingsManager.getSettingByName(this, "leftPosY").getValDouble();
        final float n6 = (float)CatClient.instance.settingsManager.getSettingByName(this, "leftPosZ").getValDouble();
        if (renderSpecificHandEvent.getHand() == EnumHand.MAIN_HAND) {
            GL11.glTranslated((double)n, (double)n2, (double)n3);
        }
        if (renderSpecificHandEvent.getHand() == EnumHand.OFF_HAND) {
            GL11.glTranslated((double)n4, (double)n5, (double)n6);
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    static {
        ViewModel.listA = new ArrayList();
    }
    
    public ViewModel() {
        super("ViewModel", "Show Tracers to players", Category.RENDER);
        CatClient.instance.settingsManager.rSetting(new Setting("rightPosX", this, 0.0, -2.0, 2.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("rightPosY", this, 0.0, -2.0, 2.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("rightPosZ", this, 0.0, -2.0, 2.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("leftPosX", this, 0.0, -2.0, 2.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("leftPosY", this, 0.0, -2.0, 2.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("leftPosZ", this, 0.0, -2.0, 2.0, false));
    }
}
