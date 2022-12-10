//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

import net.minecraft.client.*;
import java.io.*;
import javax.sound.sampled.*;

public class SoundHelper implements Helper
{
    public Minecraft mc() {
        return null;
    }
    
    private static void lambda$playSound$0(final String str, final float value, final boolean b) {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(SoundHelper.class.getResourceAsStream(String.valueOf(new StringBuilder().append("sound/").append(str))))));
            ((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(value);
            clip.start();
            if (b) {
                clip.stop();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static synchronized void playSound(final String s, final float n, final boolean b) {
        new Thread(SoundHelper::lambda$playSound$0).start();
    }
}
