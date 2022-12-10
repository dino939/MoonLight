//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.HUD;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import ru.internali.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import ru.internali.module.combat.*;
import ru.internali.utils.friend.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PlayerRadar extends Module
{
    private int x;
    private int y;
    private Entity focusTarget;
    public Minecraft mc;
    private boolean checks;
    private String enter;
    private String hef;
    private final FontRenderer fr;
    public static final PlayerRadar INSTANCE;
    private boolean old;
    private Entity target;
    
    public PlayerRadar() {
        super("PlayerRadar", "PlayerRadar", Category.HUD);
        this.mc = Minecraft.getMinecraft();
        this.checks = false;
        this.old = false;
        this.fr = Minecraft.getMinecraft().fontRenderer;
    }
    
    @SubscribeEvent
    public void onOverlayRender(final RenderGameOverlayEvent.Text text) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            this.target = null;
            if (Minecraft.getMinecraft().world == null || !Minecraft.getMinecraft().world.isRemote) {
                return;
            }
            int n = 0;
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity instanceof EntityPlayer && entity != Minecraft.getMinecraft().player) {
                    final String getName = entity.getName();
                    int n2 = Color.WHITE.getRGB();
                    if (AntiBot.isBot(entity.getName())) {
                        n2 = Color.RED.getRGB();
                    }
                    else if (FriendManager.isFriend(entity.getName())) {
                        n2 = Color.GREEN.getRGB();
                    }
                    this.fr.drawStringWithShadow(String.valueOf(new StringBuilder().append(getName).append(" [").append(((EntityPlayer)entity).getHealth()).append("/").append(((EntityPlayer)entity).getMaxHealth()).append("] [").append((int)Minecraft.getMinecraft().player.getDistance(entity)).append(']')), 0.0f, (float)(30 + n * this.fr.FONT_HEIGHT), n2);
                    ++n;
                }
            }
        }
    }
    
    static {
        INSTANCE = new PlayerRadar();
    }
}
