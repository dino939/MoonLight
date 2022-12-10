//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package ru.internali.utils;

public class AnimationUtils
{
    private TimerUtil timerUtil;
    
    public float moveUD(final float n, final float n2, final float n3, final float a) {
        float n4 = 0.0f;
        if (this.timerUtil.delay(20.0f)) {
            n4 = (n2 - n) * n3;
            if (n4 > 0.0f) {
                n4 = Math.min(n2 - n, Math.max(a, n4));
            }
            else if (n4 < 0.0f) {
                n4 = Math.max(n2 - n, Math.min(-a, n4));
            }
            this.timerUtil.reset();
        }
        return n + n4;
    }
    
    public static float calculateCompensation(final float n, float n2, long n3, final double n4) {
        final float n5 = n2 - n;
        if (n3 < 1L) {
            n3 = 1L;
        }
        if (n3 > 1000L) {
            n3 = 16L;
        }
        if (n5 > n4) {
            if ((n2 -= (float)((n4 * n3 / 16.0 < 0.5) ? 0.5 : (n4 * n3 / 16.0))) < n) {
                n2 = n;
            }
        }
        else if (n5 < -n4) {
            if ((n2 += (float)((n4 * n3 / 16.0 < 0.5) ? 0.5 : (n4 * n3 / 16.0))) > n) {
                n2 = n;
            }
        }
        else {
            n2 = n;
        }
        return n2;
    }
    
    public AnimationUtils() {
        this.timerUtil = new TimerUtil();
    }
    
    public float animate(final float n, float n2, float n3) {
        if (this.timerUtil.delay(4.0f)) {
            int n4 = 0;
            if (n <= n2) {
                n4 = 0;
            }
            final int n5 = n4;
            if (n3 < 0.0f) {
                n3 = 0.0f;
            }
            else if (n3 > 1.0) {
                n3 = 1.0f;
            }
            float n6 = (Math.max(n, n2) - Math.min(n, n2)) * n3;
            if (n6 < 0.1f) {
                n6 = 0.1f;
            }
            n2 = (n2 = ((n5 != 0) ? (n2 + n6) : (n2 - n6)));
            this.timerUtil.reset();
        }
        if (Math.abs(n2 - n) < 0.2) {
            return n;
        }
        return n2;
    }
    
    public float mvoeUD(final float n, final float n2, final float n3) {
        return this.moveUD(n, n2, 0.125f, n3);
    }
    
    public double animate(final double n, double n2, double n3) {
        if (this.timerUtil.delay(4.0f)) {
            int n4 = 0;
            if (n <= n2) {
                n4 = 0;
            }
            final int n5 = n4;
            if (n3 < 0.0) {
                n3 = 0.0;
            }
            else if (n3 > 1.0) {
                n3 = 1.0;
            }
            double n6 = (Math.max(n, n2) - Math.min(n, n2)) * n3;
            if (n6 < 0.1) {
                n6 = 0.1;
            }
            n2 = (n2 = ((n5 != 0) ? (n2 + n6) : (n2 - n6)));
            this.timerUtil.reset();
        }
        return n2;
    }
}
