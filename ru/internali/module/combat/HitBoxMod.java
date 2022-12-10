//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.module.combat;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraftforge.event.entity.living.*;
import ru.internali.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import ru.internali.utils.friend.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import ru.internali.module.*;
import ru.internali.settings.*;

public class HitBoxMod extends Module
{
    public HitBox createHitBox(final Entity entity) {
        return new HitBox(0.6f, 1.8f);
    }
    
    public void changeEntityHitBox(final Entity entity, final float width, final float height) {
        entity.width = width;
        entity.height = height;
        final double n = width / 2.0;
        entity.setEntityBoundingBox(new AxisAlignedBB(entity.posX - n, entity.posY, entity.posZ - n, entity.posX + n, entity.posY + entity.height, n + entity.posZ));
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        final float n = (float)CatClient.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
        final float n2 = (float)CatClient.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                if (entity != null && entity != Minecraft.getMinecraft().player && entity instanceof EntityPlayer && !AntiBot.isBot(entity.getName()) && !FriendManager.isFriend(entity.getName())) {
                    final HitBox hitBox = this.createHitBox(entity);
                    this.changeEntityHitBox(entity, hitBox.width + n, hitBox.height + n2);
                }
            }
        }
    }
    
    public HitBoxMod() {
        super("HitBoxMod", "big hitbox", Category.COMBAT);
        CatClient.instance.settingsManager.rSetting(new Setting("Horizontal", this, 0.1, 0.0, 3.0, false));
        CatClient.instance.settingsManager.rSetting(new Setting("Vertical", this, 0.1, 0.0, 3.0, false));
    }
    
    private static class HitBox
    {
        public float width;
        public float height;
        
        public HitBox(final float width, final float height) {
            this.width = width;
            this.height = height;
        }
    }
}
