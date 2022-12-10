//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import ru.internali.module.*;
import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import ru.internali.utils.*;
import ru.internali.*;
import org.lwjgl.opengl.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class GappelHud extends Module
{
    private final FontRenderer fr;
    public static TimerUtils timer;
    public static boolean eat;
    
    public GappelHud() {
        super("GappelHud", "GappelHud", Category.HUD);
        this.fr = Minecraft.getMinecraft().fontRenderer;
        GappelHud.timer = new TimerUtils();
    }
    
    static {
        GappelHud.eat = true;
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final ScaledResolution scaledResolution = new ScaledResolution(GappelHud.mc);
            if (GappelHud.eat) {
                GappelHud.timer.reset();
            }
            if (GappelHud.timer.isDeley(2500L)) {
                RenderUtil.drawCircle222((float)(this.getPosX() + 25), (float)(this.getPosY() + 25), 20.0f, Color.green.getRGB(), 360);
            }
            else {
                RenderUtil.drawCircle222((float)(this.getPosX() + 25), (float)(this.getPosY() + 25), 20.0f, CatClient.getClientColor().getRGB(), (int)(GappelHud.timer.getTime() / 6L));
            }
            GL11.glPushMatrix();
            RenderUtil.customScaledObject2D((float)this.getPosX(), (float)this.getPosY(), 50.0f, 50.0f, 2.0f);
            RenderUtil.renderItem(new ItemStack(Items.GOLDEN_APPLE), this.getPosX() + 25 - 8, this.getPosY() + 25 - 8);
            this.fr.drawString("", 0, 0, Color.white.getRGB());
            GL11.glPopMatrix();
        }
    }
}
