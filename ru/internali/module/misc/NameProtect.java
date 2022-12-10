//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.misc;

import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;

public class NameProtect extends Module
{
    @SubscribeEvent
    public void onRender(final RenderLivingEvent.Specials.Pre pre) {
        final EntityLivingBase entity = pre.getEntity();
        if (!(entity instanceof EntityPlayer) || entity == NameProtect.mc.player) {
            return;
        }
        if (entity.isDead || entity.getHealth() < 0.0f || entity.isInvisible()) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        pre.setCanceled(true);
    }
    
    public NameProtect() {
        super("NameProtect", "NameProtect", Category.OUTHER);
    }
}
