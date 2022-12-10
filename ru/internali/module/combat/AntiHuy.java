//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import net.minecraftforge.fml.common.gameevent.*;
import ru.internali.*;
import ru.internali.utils.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import java.util.*;
import ru.internali.settings.*;

public class AntiHuy extends Module
{
    public static List antiAimMode;
    public static List degreeMode;
    public static List antiAim;
    public float rot;
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        final String valString = CatClient.instance.settingsManager.getSettingByName(this, "antiAimMode").getValString();
        final String valString2 = CatClient.instance.settingsManager.getSettingByName(this, "antiAim").getValString();
        final String valString3 = CatClient.instance.settingsManager.getSettingByName(this, "degreeMode").getValString();
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "spinSpeed").getValDouble() * 10.0f;
        if (Objects.equals(valString, "Down")) {
            AntiHuy.mc.player.rotationYawHead = 90.0f;
        }
        else if (Objects.equals(valString, "Up")) {
            AntiHuy.mc.player.rotationYawHead = 90.0f;
        }
        else if (Objects.equals(valString, "Fake-Down")) {
            AntiHuy.mc.player.rotationYawHead = 90.0f;
        }
        else if (Objects.equals(valString, "Fake-Up")) {
            AntiHuy.mc.player.rotationYawHead = -90.0f;
        }
        if (Objects.equals(valString2, "Jitter")) {
            final float n2 = AntiHuy.mc.player.rotationYaw + 180.0f + ((AntiHuy.mc.player.ticksExisted % 8 < 4) ? MathematicHelper.randomizeFloat(-90.0f, 90.0f) : (-MathematicHelper.randomizeFloat(90.0f, -90.0f)));
            AntiHuy.mc.player.rotationYawHead = GCDCalcHelper.getFixedRotation(n2);
            AntiHuy.mc.player.renderYawOffset = n2;
            AntiHuy.mc.player.rotationYawHead = n2;
        }
        else if (valString2.equals("Freestanding")) {
            final float n3 = (float)(AntiHuy.mc.player.rotationYaw + 5.0f + Math.random() * 175.0);
            AntiHuy.mc.player.rotationYawHead = GCDCalcHelper.getFixedRotation(n3);
            AntiHuy.mc.player.renderYawOffset = n3;
            AntiHuy.mc.player.rotationYawHead = n3;
        }
        else if (valString2.equalsIgnoreCase("Spin")) {
            final float fixedRotation = GCDCalcHelper.getFixedRotation((float)(Math.floor(this.spinAim(n)) + MathematicHelper.randomizeFloat(-4.0f, 1.0f)));
            AntiHuy.mc.player.rotationYawHead = fixedRotation;
            AntiHuy.mc.player.renderYawOffset = fixedRotation;
            AntiHuy.mc.player.rotationYawHead = fixedRotation;
        }
        if (AntiHuy.mc.player.isSneaking()) {
            if (Objects.equals(valString3, "Spin")) {
                final float fixedRotation2 = GCDCalcHelper.getFixedRotation((float)(Math.floor(this.spinAim(n)) + MathematicHelper.randomizeFloat(-4.0f, 1.0f)));
                AntiHuy.mc.player.rotationYawHead = fixedRotation2;
                AntiHuy.mc.player.renderYawOffset = fixedRotation2;
                AntiHuy.mc.player.rotationYawHead = fixedRotation2;
            }
            else if (valString3.equals("Random")) {
                final float rotationYawHead = (float)(AntiHuy.mc.player.rotationYaw + Math.floor(this.spinAim(n) + ((AntiHuy.mc.player.ticksExisted % 8 < 4) ? MathematicHelper.randomizeFloat(33.0f, 22.0f) : (-MathematicHelper.randomizeFloat(33.0f, 22.0f)))));
                AntiHuy.mc.player.rotationYawHead = rotationYawHead;
                AntiHuy.mc.player.renderYawOffset = rotationYawHead;
                AntiHuy.mc.player.rotationYawHead = rotationYawHead;
            }
        }
    }
    
    public float spinAim(final float n) {
        return this.rot += n;
    }
    
    public AntiHuy() {
        super("AntiHuy", "Prevents you from targetting bots", Category.COMBAT);
        this.rot = 0.0f;
        AntiHuy.antiAimMode.add("Down");
        AntiHuy.antiAimMode.add("Up");
        AntiHuy.antiAimMode.add("Fake-Down");
        AntiHuy.antiAimMode.add("Fake-Up");
        AntiHuy.antiAimMode.add("None");
        CatClient.instance.settingsManager.rSetting(new Setting("antiAimMode", this, "2D", (ArrayList)AntiHuy.antiAimMode));
        AntiHuy.antiAim.add("Jitter");
        AntiHuy.antiAim.add("Freestanding");
        AntiHuy.antiAim.add("Spin");
        AntiHuy.antiAim.add("None");
        CatClient.instance.settingsManager.rSetting(new Setting("antiAim", this, "2D", (ArrayList)AntiHuy.antiAim));
        AntiHuy.degreeMode.add("Spin");
        AntiHuy.degreeMode.add("Random");
        AntiHuy.degreeMode.add("None");
        CatClient.instance.settingsManager.rSetting(new Setting("degreeMode", this, "2D", (ArrayList)AntiHuy.degreeMode));
        CatClient.instance.settingsManager.rSetting(new Setting("spinSpeed", this, 1.0, 0.0, 10.0, false));
    }
    
    static {
        AntiHuy.antiAimMode = new ArrayList();
        AntiHuy.antiAim = new ArrayList();
        AntiHuy.degreeMode = new ArrayList();
    }
}
