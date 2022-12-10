//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.entity.player.*;
import java.lang.reflect.*;
import net.minecraft.client.*;
import org.apache.commons.lang3.reflect.*;
import net.minecraft.entity.*;

public class ReflectionUtils
{
    public static void setSpeedInAir(final float f) {
        try {
            final Field declaredField = EntityPlayer.class.getDeclaredField(MappingManager.fieldSpeedInAir);
            declaredField.setAccessible(true);
            declaredField.setFloat(Wrapper.INSTANCE.getLocalPlayer(), f);
        }
        catch (Exception ex) {}
    }
    
    private static Object[] getTimer() {
        final Class<Minecraft> clazz = Minecraft.class;
        try {
            final Field declaredField = clazz.getDeclaredField(MappingManager.fieldTimer);
            declaredField.setAccessible(true);
            FieldUtils.removeFinalModifier(declaredField);
            final Object value = declaredField.get(Wrapper.INSTANCE.getMinecraft());
            return new Object[] { value.getClass().getDeclaredField(MappingManager.fieldTickLength), value };
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void setTimerSpeedF(final float n) {
        try {
            final Object[] timer = getTimer();
            final Field field = (Field)timer[0];
            field.setAccessible(true);
            field.setFloat(timer[1], 50.0f / n);
        }
        catch (Exception ex) {}
    }
    
    public static float getTimerSpeed() {
        try {
            final Object[] timer = getTimer();
            final Field field = (Field)timer[0];
            field.setAccessible(true);
            return field.getFloat(timer[1]);
        }
        catch (Exception ex) {
            return 0.0f;
        }
    }
    
    public static void setTimerSpeedD(final double n) {
        try {
            final Object[] timer = getTimer();
            final Field field = (Field)timer[0];
            field.setAccessible(true);
            field.setFloat(timer[1], (float)(50.0 / n));
        }
        catch (Exception ex) {}
    }
    
    public static void resetJumpTicks() {
        try {
            final Field declaredField = EntityLivingBase.class.getDeclaredField(MappingManager.fieldJumpTicks);
            declaredField.setAccessible(true);
            declaredField.setInt(Wrapper.INSTANCE.getLocalPlayer(), 0);
        }
        catch (Exception ex) {}
    }
}
